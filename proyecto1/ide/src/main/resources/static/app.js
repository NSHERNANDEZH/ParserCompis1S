// ─── Ejemplos precargados ────────────────────────────────────────────────────
const EXAMPLES = {
    hello: `// Hola Mundo en AvengerScript
nebula("¡Hola, Avengers!");
nebula("Bienvenido al IDE.");`,

    arithmetic: `// Operaciones aritméticas
stark a jarvis 15;
stark b jarvis 4;
nebula(a + b);
nebula(a - b);
nebula(a * b);
nebula(a / b);
banner pi jarvis 3.14;
nebula(pi * 2.0);`,

    ifelse: `// Estructura if / else
stark nota jarvis 75;
vision (nota > 59) {
    nebula("Aprobado");
} wanda {
    nebula("Reprobado");
}`,

    nested: `// If anidado
stark num jarvis 0;
vision (num > 0) {
    nebula("Positivo");
} wanda {
    vision (num < 0) {
        nebula("Negativo");
    } wanda {
        nebula("Cero");
    }
}`,

    while: `// Ciclo while
stark i jarvis 1;
loki (i < 6) {
    nebula(i);
    i jarvis i + 1;
}`,

    for: `// Ciclo for
fury (stark i jarvis 0; i < 5; i jarvis i + 1) {
    nebula(i);
}
nebula("Fin del ciclo.");`,

    functions: `// Funciones con retorno
stark sumar(stark a, stark b) {
    return a + b;
}

stark multiplicar(stark a, stark b) {
    return a * b;
}

stark x jarvis 10;
stark y jarvis 5;
nebula(sumar(x, y));
nebula(multiplicar(x, y));`,

    void: `// Función void (bob)
bob saludar(rogers nombre) {
    nebula("Hola, ");
    nebula(nombre);
}

saludar("Tony Stark");
saludar("Steve Rogers");`,

    input: `// Lectura de entrada con gamora
stark numero jarvis 0;
nebula("Ingresa un numero:");
gamora(numero);
nebula(numero * 2);`
};

// ─── Estado de la app ────────────────────────────────────────────────────────
let avengerEditor = null;
let javaEditor    = null;
let currentJava   = '';

// ─── Init Monaco ─────────────────────────────────────────────────────────────
require.config({ paths: { vs: 'https://unpkg.com/monaco-editor@0.44.0/min/vs' } });
require(['vs/editor/editor.main'], function () {

    // Registrar lenguaje AvengerScript
    monaco.languages.register({ id: 'avenger' });

    monaco.languages.setMonarchTokensProvider('avenger', {
        ignoreCase: false,
        defaultToken: '',
        tokenizer: {
            root: [
                [/\/\/.*$/, 'comment'],
                [/\b(stark|banner|rogers|thor|bob)\b/, 'keyword.type'],
                [/\b(vision|wanda|loki|fury)\b/, 'keyword.control'],
                [/\b(gamora|nebula)\b/, 'keyword.io'],
                [/\b(recruit|assemble)\b/, 'keyword.module'],
                [/\breturn\b/, 'keyword.return'],
                [/\bjarvis\b/, 'keyword.jarvis'],
                [/\b(TRUE|FALSE)\b/, 'constant.boolean'],
                [/"[^"]*"/, 'string'],
                [/\b\d+\.\d+\b/, 'number.float'],
                [/\b\d+\b/, 'number'],
                [/==|!=|<=|>=|<|>/, 'operator.compare'],
                [/[+\-*\/]/, 'operator.arith'],
                [/[{}()\[\]]/, 'delimiter'],
                [/[;,]/, 'punctuation'],
                [/[a-zA-Z_][a-zA-Z0-9_]*/, 'identifier'],
            ]
        }
    });

    // Tema personalizado Avengers
    monaco.editor.defineTheme('avengers-dark', {
        base: 'vs-dark',
        inherit: true,
        rules: [
            { token: 'comment',          foreground: '5c6e8c', fontStyle: 'italic' },
            { token: 'keyword.type',     foreground: 'e63946', fontStyle: 'bold' },
            { token: 'keyword.control',  foreground: 'ffd700', fontStyle: 'bold' },
            { token: 'keyword.io',       foreground: '61afef' },
            { token: 'keyword.module',   foreground: 'c678dd' },
            { token: 'keyword.return',   foreground: 'c678dd', fontStyle: 'italic' },
            { token: 'keyword.jarvis',   foreground: 'f77f00' },
            { token: 'constant.boolean', foreground: '56b6c2' },
            { token: 'string',           foreground: '98c379' },
            { token: 'number',           foreground: 'd19a66' },
            { token: 'number.float',     foreground: 'd19a66' },
            { token: 'operator.compare', foreground: 'e5c07b' },
            { token: 'operator.arith',   foreground: 'e5c07b' },
            { token: 'delimiter',        foreground: 'abb2bf' },
            { token: 'punctuation',      foreground: '636d83' },
            { token: 'identifier',       foreground: 'abb2bf' },
        ],
        colors: {
            'editor.background':                       '#0d0d1a',
            'editor.foreground':                       '#abb2bf',
            'editorLineNumber.foreground':             '#3a3a5c',
            'editorLineNumber.activeForeground':       '#888',
            'editor.selectionBackground':              '#264f7844',
            'editor.lineHighlightBackground':          '#16213e',
            'editorCursor.foreground':                 '#ffd700',
            'editorIndentGuide.background':            '#252545',
            'editorSuggestWidget.background':          '#181c28',
            'editorSuggestWidget.border':              '#263650',
            'editorSuggestWidget.foreground':          '#dce8f0',
            'editorSuggestWidget.selectedBackground':  '#2e74c0',
            'editorSuggestWidget.selectedForeground':  '#ffffff',
            'editorSuggestWidget.highlightForeground': '#f0a500',
        }
    });

    // Editor izquierdo (AvengerScript)
    avengerEditor = monaco.editor.create(document.getElementById('avengerEditorWrap'), {
        language: 'avenger',
        theme: 'avengers-dark',
        value: EXAMPLES.hello,
        fontSize: 14,
        minimap: { enabled: false },
        automaticLayout: true,
        scrollBeyondLastLine: false,
        wordWrap: 'on',
        tabSize: 4,
        insertSpaces: true,
        lineNumbers: 'on',
        renderLineHighlight: 'all',
        quickSuggestions: false,
        suggestOnTriggerCharacters: false,
    });

    // Editor derecho (Java generado — solo lectura)
    javaEditor = monaco.editor.create(document.getElementById('javaEditorWrap'), {
        language: 'java',
        theme: 'avengers-dark',
        value: '// El código Java generado aparecerá aquí...\n// Presiona "Compilar" (Ctrl+Shift+B) o "Ejecutar" (Ctrl+Enter).',
        readOnly: true,
        fontSize: 13,
        minimap: { enabled: false },
        automaticLayout: true,
        scrollBeyondLastLine: false,
        lineNumbers: 'on',
        renderLineHighlight: 'all',
    });

    // Atajos de teclado
    avengerEditor.addCommand(
        monaco.KeyMod.CtrlCmd | monaco.KeyCode.Enter,
        () => doRun()
    );
    avengerEditor.addCommand(
        monaco.KeyMod.CtrlCmd | monaco.KeyMod.Shift | monaco.KeyCode.KeyB,
        () => doCompile()
    );

});


// ─── Ejemplos ────────────────────────────────────────────────────────────────
document.getElementById('exampleSelect').addEventListener('change', function () {
    const val = this.value;
    if (val && avengerEditor) {
        avengerEditor.setValue(EXAMPLES[val] || '');
        this.value = '';
        javaEditor.setValue('// Cargado ejemplo. Presiona ▶ Ejecutar para ver el resultado.');
        document.getElementById('btnDownload').disabled = true;
        currentJava = '';
        setStatus('Ejemplo cargado — listo para compilar.', '');
    }
});

// ─── Tabs de consola ─────────────────────────────────────────────────────────
function switchTab(el, name) {
    document.querySelectorAll('.ctab').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.cpanel').forEach(p => p.classList.remove('active'));
    el.classList.add('active');
    document.getElementById('panel-' + name).classList.add('active');
}

function activateTab(name) {
    const tab = document.querySelector(`.ctab[data-tab="${name}"]`);
    if (tab) switchTab(tab, name);
}

// ─── Status bar ───────────────────────────────────────────────────────────────
function setStatus(msg, color) {
    const el = document.getElementById('statusBar');
    el.textContent = msg;
    el.style.color = color || 'var(--text-muted)';
}

// ─── Loading ──────────────────────────────────────────────────────────────────
function showLoading(msg) {
    document.getElementById('loading-msg').textContent = msg || 'Procesando...';
    document.getElementById('loading').classList.add('show');
}
function hideLoading() {
    document.getElementById('loading').classList.remove('show');
}

// ─── Mostrar errores ──────────────────────────────────────────────────────────
function showErrors(errors) {
    const panel = document.getElementById('panel-errors');
    const badge = document.getElementById('errBadge');

    if (!errors || errors.length === 0) {
        panel.innerHTML = '<span class="ok-line">✓ Sin errores de compilación.</span>';
        badge.style.display = 'none';
    } else {
        panel.innerHTML = errors.map(e => {
            let cls = 'err-generacion';
            if (e.includes('LÉXICO'))      cls = 'err-lexico';
            else if (e.includes('SINT'))   cls = 'err-sintactico';
            else if (e.includes('SEM') || e.includes('sem') || e.includes('ariable') || e.includes('unción')) cls = 'err-semantico';
            return `<div class="err-line ${cls}">${esc(e)}</div>`;
        }).join('');
        badge.textContent = errors.length;
        badge.style.display = 'inline';
        activateTab('errors');
    }
}

// ─── Mostrar tokens ───────────────────────────────────────────────────────────
function showTokens(tokens) {
    const panel = document.getElementById('panel-tokens');
    if (!tokens || tokens.length === 0) {
        panel.innerHTML = '<span class="out-dim">Sin tokens.</span>';
        return;
    }

    const typeClass = t => {
        if (/STARK|BANNER|ROGERS|THOR|BOB|VISION|WANDA|LOKI|FURY|GAMORA|NEBULA|RECRUIT|ASSEMBLE|RETURN|JARVIS/.test(t))
            return 'token-kw';
        if (/NUMERO/.test(t)) return 'token-num';
        if (/STRING/.test(t)) return 'token-str';
        if (/IDENTIFICADOR/.test(t)) return 'token-id';
        if (/PLUS|MINUS|MULT|DIV|PARKER|ODIN|NOJARVIS|JARVISJARVIS/.test(t)) return 'token-op';
        return 'token-sym';
    };

    const rows = tokens.map(tok =>
        `<tr>
            <td class="${typeClass(tok.type)}">${esc(tok.type)}</td>
            <td style="color:#abb2bf">${esc(tok.value)}</td>
            <td style="color:#636d83">${tok.line}</td>
            <td style="color:#636d83">${tok.column}</td>
        </tr>`
    ).join('');

    panel.innerHTML = `
        <table class="token-table">
            <thead><tr>
                <th>Tipo de Token</th>
                <th>Lexema</th>
                <th>Línea</th>
                <th>Columna</th>
            </tr></thead>
            <tbody>${rows}</tbody>
        </table>`;
}

// ─── Compilar ─────────────────────────────────────────────────────────────────
async function doCompile() {
    if (!avengerEditor) return;
    const code = avengerEditor.getValue().trim();
    if (!code) { setStatus('Editor vacío.', 'orange'); return; }

    showLoading('🔧 Compilando AvengerScript...');
    setStatus('Compilando...', 'var(--accent-gold)');
    document.getElementById('btnCompile').disabled = true;
    document.getElementById('btnRun').disabled = true;

    try {
        const res = await fetch('/api/compile', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ code })
        });
        const data = await res.json();

        showErrors(data.errors);
        showTokens(data.tokens);

        if (data.javaCode) {
            currentJava = data.javaCode;
            javaEditor.setValue(data.javaCode);
            document.getElementById('btnDownload').disabled = false;
            setStatus(`✓ Compilado — ${(data.tokens || []).length} tokens`, 'var(--success)');
        } else {
            javaEditor.setValue('// Error de compilación — revisa la pestaña Errores.');
            setStatus(`✗ ${(data.errors || []).length} error(es) de compilación`, 'var(--error)');
        }
    } catch (err) {
        setStatus('Error de conexión con el servidor.', 'var(--error)');
    } finally {
        hideLoading();
        document.getElementById('btnCompile').disabled = false;
        document.getElementById('btnRun').disabled = false;
    }
}

// ─── Terminal WebSocket ───────────────────────────────────────────────────────
let activeSocket = null;

function terminalAppend(text, cls) {
    const terminal = document.getElementById('terminal');
    const span = document.createElement('span');
    span.className = cls || '';
    span.textContent = text;
    terminal.appendChild(span);
    terminal.scrollTop = terminal.scrollHeight;
}

function terminalClear() {
    document.getElementById('terminal').innerHTML = '';
}

function setInputVisible(visible) {
    const row   = document.getElementById('terminalInputRow');
    const input = document.getElementById('terminalInput');
    row.style.display = visible ? 'flex' : 'none';
    if (visible) { input.value = ''; input.focus(); }
}

// ─── Ejecutar ─────────────────────────────────────────────────────────────────
function doRun() {
    if (!avengerEditor) return;
    const code = avengerEditor.getValue().trim();
    if (!code) { setStatus('Editor vacío.', 'orange'); return; }

    // Cerrar socket previo si existe
    if (activeSocket) { activeSocket.close(); activeSocket = null; }

    terminalClear();
    setInputVisible(false);
    activateTab('output');
    setStatus('Conectando...', 'var(--gold)');
    document.getElementById('btnRun').disabled = true;

    const ws = new WebSocket(`ws://${location.host}/ws/terminal`);
    activeSocket = ws;

    ws.onopen = () => {
        setStatus('Compilando y ejecutando...', 'var(--gold)');
        ws.send(JSON.stringify({ type: 'run', code }));
    };

    ws.onmessage = (ev) => {
        const msg = JSON.parse(ev.data);

        switch (msg.type) {
            case 'stdout':
                terminalAppend(msg.data, 'out-ok');
                break;

            case 'stderr':
                terminalAppend(msg.data, 'out-err');
                break;

            case 'javaCode':
                currentJava = msg.data;
                javaEditor.setValue(msg.data);
                document.getElementById('btnDownload').disabled = false;
                break;

            case 'tokens':
                showTokens(msg.extra);
                break;

            case 'errors':
                showErrors(msg.extra);
                break;

            case 'exit': {
                const ok = msg.data === '0';
                setInputVisible(false);
                setStatus(ok ? '✓ Ejecución exitosa' : '✗ Terminó con error', ok ? 'var(--success)' : 'var(--red)');
                document.getElementById('btnRun').disabled = false;
                activeSocket = null;
                break;
            }
        }

        // Mostrar input cuando el proceso está corriendo y llega output
        if (msg.type === 'stdout' || msg.type === 'stderr') {
            setInputVisible(true);
        }
    };

    ws.onerror = () => {
        terminalAppend('\nError de conexión WebSocket.\n', 'out-err');
        setStatus('Error de conexión', 'var(--red)');
        document.getElementById('btnRun').disabled = false;
        setInputVisible(false);
    };

    ws.onclose = () => {
        setInputVisible(false);
        document.getElementById('btnRun').disabled = false;
        activeSocket = null;
    };
}

// Enviar stdin al presionar Enter
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('terminalInput').addEventListener('keydown', (e) => {
        if (e.key === 'Enter' && activeSocket && activeSocket.readyState === WebSocket.OPEN) {
            const val = e.target.value;
            terminalAppend(val + '\n', 'terminal-echo');
            activeSocket.send(JSON.stringify({ type: 'stdin', data: val }));
            e.target.value = '';
        }
    });
});

// ─── Descargar .java ──────────────────────────────────────────────────────────
function doDownload() {
    if (!currentJava) return;
    const blob = new Blob([currentJava], { type: 'text/plain;charset=utf-8' });
    const url  = URL.createObjectURL(blob);
    const a    = Object.assign(document.createElement('a'), {
        href: url, download: 'Traduccion.java'
    });
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
    setStatus('✓ Descargado: Traduccion.java', 'var(--success)');
}

// ─── Utilidades ───────────────────────────────────────────────────────────────
function esc(s) {
    return String(s)
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;');
}

// ─── Splitters redimensionables ───────────────────────────────────────────────
(function () {
    // Splitter vertical (entre editores izq/der)
    const splitterV  = document.getElementById('splitterV');
    const paneLeft   = document.getElementById('paneAvenger');
    const paneRight  = document.getElementById('paneJava');
    const editorsRow = document.getElementById('editorsRow');

    splitterV.addEventListener('mousedown', function (e) {
        e.preventDefault();
        splitterV.classList.add('dragging');
        document.body.style.cursor = 'col-resize';
        document.body.style.userSelect = 'none';

        const startX     = e.clientX;
        const totalW     = editorsRow.clientWidth - splitterV.offsetWidth;
        const startLeftW = paneLeft.offsetWidth;

        function onMove(ev) {
            const delta = ev.clientX - startX;
            const newLeft = Math.min(Math.max(startLeftW + delta, totalW * 0.2), totalW * 0.8);
            paneLeft.style.flex  = 'none';
            paneRight.style.flex = 'none';
            paneLeft.style.width  = newLeft + 'px';
            paneRight.style.width = (totalW - newLeft) + 'px';
        }

        function onUp() {
            splitterV.classList.remove('dragging');
            document.body.style.cursor = '';
            document.body.style.userSelect = '';
            document.removeEventListener('mousemove', onMove);
            document.removeEventListener('mouseup', onUp);
        }

        document.addEventListener('mousemove', onMove);
        document.addEventListener('mouseup', onUp);
    });

    // Splitter horizontal (entre editores y consola)
    const splitterH  = document.getElementById('splitterH');
    const consoleEl  = document.getElementById('consolePanel');
    const mainEl     = document.querySelector('.main');

    splitterH.addEventListener('mousedown', function (e) {
        e.preventDefault();
        splitterH.classList.add('dragging');
        document.body.style.cursor = 'row-resize';
        document.body.style.userSelect = 'none';

        const startY      = e.clientY;
        const startConsH  = consoleEl.offsetHeight;
        const mainH       = mainEl.offsetHeight;

        function onMove(ev) {
            const delta   = startY - ev.clientY;
            const newConsH = Math.min(Math.max(startConsH + delta, 80), mainH * 0.7);
            consoleEl.style.height = newConsH + 'px';
        }

        function onUp() {
            splitterH.classList.remove('dragging');
            document.body.style.cursor = '';
            document.body.style.userSelect = '';
            document.removeEventListener('mousemove', onMove);
            document.removeEventListener('mouseup', onUp);
        }

        document.addEventListener('mousemove', onMove);
        document.addEventListener('mouseup', onUp);
    });
})();
