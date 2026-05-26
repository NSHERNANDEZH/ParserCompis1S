import * as vscode from 'vscode';
import * as http from 'http';
import * as path from 'path';
import * as fs from 'fs';

// ─── Tipos que el servidor devuelve ──────────────────────────────────────────
interface TokenInfo {
    type: string;
    value: string;
    line: number;
    column: number;
}
interface CompileResponse {
    javaCode: string | null;
    errors: string[];
    tokens: TokenInfo[];
}
interface RunResponse {
    success: boolean;
    javaCode: string | null;
    output: string | null;
    errorMsg: string | null;
    errors: string[];
    tokens: TokenInfo[];
}

// ─── Estado global ────────────────────────────────────────────────────────────
const DIAGNOSTICS = vscode.languages.createDiagnosticCollection('avengerscript');
let OUTPUT_CHANNEL: vscode.OutputChannel;
let JAVA_CHANNEL: vscode.OutputChannel;
let STATUS_BAR: vscode.StatusBarItem;
let lastJavaCode: string = '';

// ─── Activación ──────────────────────────────────────────────────────────────
export function activate(context: vscode.ExtensionContext) {
    OUTPUT_CHANNEL = vscode.window.createOutputChannel('AvengerScript — Salida');
    JAVA_CHANNEL   = vscode.window.createOutputChannel('AvengerScript — Java Generado');

    // Status bar
    STATUS_BAR = vscode.window.createStatusBarItem(vscode.StatusBarAlignment.Left, 100);
    STATUS_BAR.command = 'avengerscript.compile';
    context.subscriptions.push(STATUS_BAR);

    // Compilar automáticamente al guardar
    context.subscriptions.push(
        vscode.workspace.onDidSaveTextDocument(doc => {
            if (doc.languageId !== 'avengerscript') { return; }
            const cfg = vscode.workspace.getConfiguration('avengerscript');
            if (cfg.get<boolean>('autoCompileOnSave', true)) {
                compileDocument(doc);
            }
        })
    );

    // Actualizar status bar al cambiar el editor activo
    context.subscriptions.push(
        vscode.window.onDidChangeActiveTextEditor(editor => updateStatusBar(editor))
    );

    // Registrar comandos
    context.subscriptions.push(
        vscode.commands.registerCommand('avengerscript.compile', cmdCompile),
        vscode.commands.registerCommand('avengerscript.run',     cmdRun),
        vscode.commands.registerCommand('avengerscript.showJava', cmdShowJava),
        vscode.commands.registerCommand('avengerscript.startServer', cmdStartServer)
    );

    // Estado inicial
    updateStatusBar(vscode.window.activeTextEditor);

    // Compilar el archivo actual si ya está abierto
    const activeEditor = vscode.window.activeTextEditor;
    if (activeEditor && activeEditor.document.languageId === 'avengerscript') {
        compileDocument(activeEditor.document);
    }

    console.log('[AvengerScript] Extensión activada');
}

export function deactivate() {
    DIAGNOSTICS.clear();
    DIAGNOSTICS.dispose();
}

// ─── Status bar ───────────────────────────────────────────────────────────────
function updateStatusBar(editor: vscode.TextEditor | undefined) {
    if (!editor || editor.document.languageId !== 'avengerscript') {
        STATUS_BAR.hide();
        return;
    }
    STATUS_BAR.text = '$(shield) AvengerScript';
    STATUS_BAR.tooltip = 'Compilar (Ctrl+Shift+B) / Ejecutar (F5)';
    STATUS_BAR.show();
}

// ─── Comando: Compilar ────────────────────────────────────────────────────────
async function cmdCompile() {
    const editor = vscode.window.activeTextEditor;
    if (!editor || editor.document.languageId !== 'avengerscript') {
        vscode.window.showWarningMessage('Abrí un archivo .avng para compilar.');
        return;
    }
    await editor.document.save();
    const result = await compileDocument(editor.document);
    if (!result) { return; }

    if (result.errors.length === 0 && result.javaCode) {
        vscode.window.showInformationMessage(
            `✓ Compilado — ${result.tokens.length} tokens`,
            'Ver Java'
        ).then(sel => { if (sel === 'Ver Java') { cmdShowJava(); } });
    } else {
        vscode.window.showErrorMessage(
            `✗ ${result.errors.length} error(es) — ver pestaña Errores`
        );
    }

    // Mostrar tokens si está habilitado
    const cfg = vscode.workspace.getConfiguration('avengerscript');
    if (cfg.get<boolean>('showTokensOnCompile', false) && result.tokens.length > 0) {
        OUTPUT_CHANNEL.appendLine('\n─── Tokens ──────────────────────');
        result.tokens.forEach(t =>
            OUTPUT_CHANNEL.appendLine(`  ${t.type.padEnd(20)} "${t.value}"  L${t.line}:${t.column}`)
        );
        OUTPUT_CHANNEL.show(true);
    }
}

// ─── Comando: Ejecutar ────────────────────────────────────────────────────────
async function cmdRun() {
    const editor = vscode.window.activeTextEditor;
    if (!editor || editor.document.languageId !== 'avengerscript') {
        vscode.window.showWarningMessage('Abrí un archivo .avng para ejecutar.');
        return;
    }
    await editor.document.save();
    const code = editor.document.getText();

    STATUS_BAR.text = '$(sync~spin) Ejecutando...';

    // Preguntar stdin
    const stdin = await vscode.window.showInputBox({
        prompt: 'Entrada del programa (deja vacío si no necesita gamora)',
        placeHolder: 'ej: 42  ó  Hola Mundo',
        ignoreFocusOut: true
    }) ?? '';

    try {
        const serverUrl = getServerUrl();
        const result = await httpPost<RunResponse>(`${serverUrl}/api/run`, { code, stdin });

        OUTPUT_CHANNEL.clear();
        OUTPUT_CHANNEL.appendLine(`═══ AvengerScript: Ejecutar ═══  ${timestamp()}`);
        OUTPUT_CHANNEL.appendLine(`Archivo: ${path.basename(editor.document.fileName)}`);
        OUTPUT_CHANNEL.appendLine('─────────────────────────────────');

        if (result.output) {
            OUTPUT_CHANNEL.appendLine(result.output);
        }
        if (result.errorMsg) {
            OUTPUT_CHANNEL.appendLine('\n⚠ ' + result.errorMsg);
        }
        OUTPUT_CHANNEL.appendLine('─────────────────────────────────');
        OUTPUT_CHANNEL.appendLine(result.success ? '✓ Ejecución exitosa' : '✗ Terminó con error');
        OUTPUT_CHANNEL.show(true);

        // Actualizar diagnósticos y Java si hay datos nuevos
        applyDiagnostics(editor.document, result.errors);
        if (result.javaCode) {
            lastJavaCode = result.javaCode;
        }

        STATUS_BAR.text = result.success
            ? '$(pass) AvengerScript'
            : '$(error) AvengerScript';

    } catch (err: any) {
        STATUS_BAR.text = '$(shield) AvengerScript';
        showServerError(err.message ?? String(err));
    }
}

// ─── Comando: Ver Java generado ───────────────────────────────────────────────
async function cmdShowJava() {
    if (!lastJavaCode) {
        vscode.window.showWarningMessage('Compilá el archivo primero para ver el Java generado.');
        return;
    }
    JAVA_CHANNEL.clear();
    JAVA_CHANNEL.appendLine('// Java generado por AvengerScript Compiler');
    JAVA_CHANNEL.appendLine('// ─────────────────────────────────────────');
    JAVA_CHANNEL.appendLine(lastJavaCode);
    JAVA_CHANNEL.show(false);
}

// ─── Comando: Iniciar servidor ────────────────────────────────────────────────
async function cmdStartServer() {
    // Buscar run-ide.bat subiendo desde el workspace
    const workspaceFolders = vscode.workspace.workspaceFolders;
    if (!workspaceFolders) {
        vscode.window.showErrorMessage('No hay workspace abierto.');
        return;
    }

    let batPath: string | undefined;
    for (const folder of workspaceFolders) {
        const candidate = path.join(folder.uri.fsPath, 'run-ide.bat');
        if (fs.existsSync(candidate)) { batPath = candidate; break; }
        // Subir un nivel
        const parent = path.join(folder.uri.fsPath, '..', 'run-ide.bat');
        if (fs.existsSync(parent)) { batPath = parent; break; }
    }

    if (!batPath) {
        vscode.window.showErrorMessage(
            'No se encontró run-ide.bat. Abrí la carpeta raíz del proyecto AvengerScript.'
        );
        return;
    }

    const terminal = vscode.window.createTerminal('AvengerScript IDE Server');
    terminal.sendText(`"${batPath}"`);
    terminal.show();
    vscode.window.showInformationMessage(
        'Iniciando servidor AvengerScript IDE... esperá unos segundos.'
    );
}

// ─── Core: compilar documento ─────────────────────────────────────────────────
async function compileDocument(doc: vscode.TextDocument): Promise<CompileResponse | null> {
    STATUS_BAR.text = '$(sync~spin) Compilando...';

    try {
        const serverUrl = getServerUrl();
        const result = await httpPost<CompileResponse>(`${serverUrl}/api/compile`, {
            code: doc.getText()
        });

        applyDiagnostics(doc, result.errors);
        if (result.javaCode) {
            lastJavaCode = result.javaCode;
        }

        const errCount = result.errors.length;
        STATUS_BAR.text = errCount === 0
            ? '$(pass) AvengerScript'
            : `$(error) AvengerScript (${errCount} error${errCount > 1 ? 'es' : ''})`;

        return result;

    } catch (err: any) {
        // Servidor no disponible → no spamear, solo actualizar status bar
        STATUS_BAR.text = '$(warning) AvengerScript (sin servidor)';
        STATUS_BAR.tooltip = 'El servidor AvengerScript no está corriendo. Usá: AvengerScript: Iniciar servidor IDE';
        return null;
    }
}

// ─── Diagnósticos ─────────────────────────────────────────────────────────────
function applyDiagnostics(doc: vscode.TextDocument, errors: string[]) {
    if (!errors || errors.length === 0) {
        DIAGNOSTICS.set(doc.uri, []);
        return;
    }

    const diags: vscode.Diagnostic[] = errors.map(err => {
        const { line, col, msg } = parseErrorLine(err);

        // Ajustar al rango real del documento
        const safeLine = Math.min(Math.max(line, 0), doc.lineCount - 1);
        const lineText = doc.lineAt(safeLine).text;
        const endCol   = lineText.length;

        const range = new vscode.Range(
            safeLine, col,
            safeLine, Math.max(col + 1, endCol)
        );

        const severity = err.includes('LÉXICO') || err.includes('SINTÁCTICO')
            ? vscode.DiagnosticSeverity.Error
            : err.includes('SEM') || err.includes('ariable') || err.includes('unción')
                ? vscode.DiagnosticSeverity.Warning
                : vscode.DiagnosticSeverity.Error;

        const diag = new vscode.Diagnostic(range, msg, severity);
        diag.source = 'AvengerScript';
        return diag;
    });

    DIAGNOSTICS.set(doc.uri, diags);
}

// ─── Parsear "línea N:M" del mensaje de error ─────────────────────────────────
function parseErrorLine(error: string): { line: number; col: number; msg: string } {
    // Formato ANTLR: "línea 3:5 ..."
    const m = error.match(/l[ií]nea\s+(\d+):(\d+)/i)
           ?? error.match(/line\s+(\d+):(\d+)/i);
    if (m) {
        return { line: parseInt(m[1]) - 1, col: parseInt(m[2]), msg: error };
    }
    // Fallback: buscar cualquier número de línea mencionado
    const lineOnly = error.match(/\bl[ií]nea\s+(\d+)\b/i);
    if (lineOnly) {
        return { line: parseInt(lineOnly[1]) - 1, col: 0, msg: error };
    }
    return { line: 0, col: 0, msg: error };
}

// ─── HTTP helper ──────────────────────────────────────────────────────────────
function httpPost<T>(url: string, body: object): Promise<T> {
    return new Promise((resolve, reject) => {
        const json     = JSON.stringify(body);
        const parsed   = new URL(url);
        const options: http.RequestOptions = {
            hostname: parsed.hostname,
            port:     parsed.port ? parseInt(parsed.port) : 8080,
            path:     parsed.pathname,
            method:   'POST',
            headers:  {
                'Content-Type':   'application/json',
                'Content-Length': Buffer.byteLength(json)
            }
        };

        const req = http.request(options, res => {
            let data = '';
            res.setEncoding('utf8');
            res.on('data', chunk => data += chunk);
            res.on('end', () => {
                try { resolve(JSON.parse(data) as T); }
                catch { reject(new Error(`Respuesta inválida del servidor: ${data.substring(0, 100)}`)); }
            });
        });

        req.on('error', err => {
            if ((err as NodeJS.ErrnoException).code === 'ECONNREFUSED') {
                reject(new Error('ECONNREFUSED'));
            } else {
                reject(err);
            }
        });

        req.setTimeout(15000, () => {
            req.destroy();
            reject(new Error('Timeout: el servidor no respondió en 15s'));
        });

        req.write(json);
        req.end();
    });
}

// ─── Error de servidor: mostrar mensaje con acción ───────────────────────────
function showServerError(msg: string) {
    const isDown = msg.includes('ECONNREFUSED');
    const text = isDown
        ? 'El servidor AvengerScript IDE no está corriendo.'
        : `Error de conexión: ${msg}`;

    vscode.window.showErrorMessage(text, 'Iniciar servidor').then(sel => {
        if (sel === 'Iniciar servidor') {
            vscode.commands.executeCommand('avengerscript.startServer');
        }
    });
}

// ─── Helpers ──────────────────────────────────────────────────────────────────
function getServerUrl(): string {
    return vscode.workspace
        .getConfiguration('avengerscript')
        .get<string>('serverUrl', 'http://localhost:8080')
        .replace(/\/$/, '');
}

function timestamp(): string {
    return new Date().toLocaleTimeString('es-GT');
}
