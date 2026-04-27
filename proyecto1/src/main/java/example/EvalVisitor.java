package example;
import org.antlr.v4.runtime.*;
import antlr.*;
import java.util.*;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Recorre el árbol de análisis sintáctico y:
//1. Construye una cadena de código Java (traducción).
//2. Registra cada variable declarada en la tabla de símbolos (List<Variable>).

public class EvalVisitor extends AvengerBaseVisitor<String> {

    // ── Salidas ───────────────────────────────────────────────────────────────
    private final StringBuilder javaCode = new StringBuilder();
    private List<Variable> symbolTable = new ArrayList<>();
    private Set<String> archivosEnsamblados = new HashSet<>();
    private int indentLevel = 0;
    private String currentScope = "global";
    private final boolean esHijo;
    // ── Accesores públicos ────────────────────────────────────────────────────
    public String getJavaCode()            { return javaCode.toString(); }
    public List<Variable> getSymbolTable() { return symbolTable; }
    // ── Constructores ────────────────────────────────────────────────────────────
    //Constructor principal — usado al visitar el archivo raíz.
    public EvalVisitor() {
        this.symbolTable          = new ArrayList<>();
        this.archivosEnsamblados  = new HashSet<>();
        this.esHijo               = false;
    }

    //Constructor hijo — usado al ensamblar un archivo externo.
    //Comparte tabla de símbolos y conjunto de ensamblados con el padre
    //para que las variables y funciones del archivo externo sean visibles
    //en el scope del archivo que lo ensambló.
    public EvalVisitor(List<Variable> symbolTable, Set<String> archivosEnsamblados, int indentLevel, String currentScope) {
        this.symbolTable         = symbolTable;
        this.archivosEnsamblados = archivosEnsamblados;
        this.indentLevel         = indentLevel;
        this.currentScope        = currentScope;
        this.esHijo              = true;
    }

    // ── Utilidades ────────────────────────────────────────────────────────────
    private String indent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) sb.append("    ");
        return sb.toString();
    }

    private String mapType(String avengerType) {
        switch (avengerType) {
            case "stark":  return "int";
            case "banner": return "double";
            case "rogers": return "String";
            case "thor":   return "boolean";
            default:       return avengerType;
        }
    }

    private String buildFuncCall(String name, List<AvengerParser.ExprContext> args) {
        StringBuilder sb = new StringBuilder(name).append("(");
        for (int i = 0; i < args.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visit(args.get(i)));
        }
        sb.append(")");
        return sb.toString();
    }

    private void appendParams(List<AvengerParser.ParamContext> params) {
        for (int i = 0; i < params.size(); i++) {
            if (i > 0) javaCode.append(", ");
            javaCode.append(visit(params.get(i)));
        }
    }

    // =========================================================================
    // prog : statement* EOF
    // =========================================================================
    @Override
    public String visitProg(AvengerParser.ProgContext ctx) {
        javaCode.append("import java.util.Scanner;\n\n"); //Se puede omitir
        javaCode.append("public class Traduccion {\n");
        indentLevel++;
        javaCode.append(indent()).append("public static void main(String[] args) {\n");
        indentLevel++;
        javaCode.append(indent()).append("Scanner scanner = new Scanner(System.in);\n\n");

        visitChildren(ctx);

        indentLevel--;
        javaCode.append(indent()).append("}\n");
        indentLevel--;
        javaCode.append("}\n");
        return null;
    }

    // =========================================================================
    // alternativas de statement
    // =========================================================================

    // =========================================================================
    // Produccion 1: tipoVar IDENTIFICADOR JARVIS expr SEMI   #StmtVarDecl
    // =========================================================================
    @Override
    public String visitStmtVarDecl(AvengerParser.StmtVarDeclContext ctx) {
        String javaType = mapType(ctx.tipoVar().getText());
        String name     = ctx.IDENTIFICADOR().getText();
        String value    = visit(ctx.expr());

        javaCode.append(indent())
                .append(javaType).append(" ")
                .append(name).append(" = ")
                .append(value).append(";\n");

        symbolTable.add(new Variable(name, value, javaType, currentScope));
        return null;
    }
    // =========================================================================
    // Produccion 2: IDENTIFICADOR JARVIS expr SEMI   #StmtAssign
    // =========================================================================
    @Override
    public String visitStmtAssign(AvengerParser.StmtAssignContext ctx) {
        String name  = ctx.IDENTIFICADOR().getText();
        String value = visit(ctx.expr());

        javaCode.append(indent())
                .append(name).append(" = ")
                .append(value).append(";\n");

        // Actualiza la entrada existente o agrega con tipo desconocido si no fue declarada
        java.util.Optional<Variable> existing = symbolTable.stream()
                .filter(v -> v.getName().equals(name) && v.getScope().equals(currentScope))
                .findFirst();
        if (existing.isPresent()) {
            existing.get().setValue(value);
        } else {
            symbolTable.add(new Variable(name, value, "unknown", currentScope));
        }
        return null;
    }

    // Produccion 3: VISION LPAREN condition RPAREN LBRACE statement* RBRACE
    //               (WANDA LBRACE statement* RBRACE)?   #StmtIf
    @Override
    public String visitStmtIf(AvengerParser.StmtIfContext ctx) {
        String condition = visit(ctx.condition());

        javaCode.append(indent()).append("if (").append(condition).append(") {\n");

        String outerScope = currentScope;
        currentScope = currentScope + ".if";
        indentLevel++;

        // Separa los statements del bloque if y del bloque else usando la posición del token WANDA
        List<AvengerParser.StatementContext> stmts = ctx.statement();
        int splitIndex = stmts.size(); // por defecto: todos los statements pertenecen al bloque if

        int wandaPos = -1;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i).getText().equals("wanda")) {
                wandaPos = ctx.getChild(i).getSourceInterval().a;
                break;
            }
        }

        if (wandaPos != -1) {
            splitIndex = 0;
            for (AvengerParser.StatementContext s : stmts) {
                if (s.getStart().getTokenIndex() < wandaPos) {
                    splitIndex++;
                }
            }
        }

        for (int i = 0; i < splitIndex; i++) {
            visit(stmts.get(i));
        }

        indentLevel--;
        currentScope = outerScope;

        // Bloque else (WANDA)
        if (wandaPos != -1) {
            javaCode.append(indent()).append("} else {\n");
            currentScope = currentScope + ".else";
            indentLevel++;

            for (int i = splitIndex; i < stmts.size(); i++) {
                visit(stmts.get(i));
            }

            indentLevel--;
            currentScope = outerScope;
        }

        javaCode.append(indent()).append("}\n");
        return null;
    }
    // =========================================================================
    // Produccion 4: LOKI LPAREN condition RPAREN LBRACE statement* RBRACE   #StmtWhile
    // =========================================================================
    @Override
    public String visitStmtWhile(AvengerParser.StmtWhileContext ctx) {
        String condition = visit(ctx.condition());

        javaCode.append(indent()).append("while (").append(condition).append(") {\n");

        String outerScope = currentScope;
        currentScope = currentScope + ".while";
        indentLevel++;

        for (AvengerParser.StatementContext s : ctx.statement()) {
            visit(s);
        }

        indentLevel--;
        currentScope = outerScope;
        javaCode.append(indent()).append("}\n");
        return null;
    }
    // =========================================================================
    // Produccion 5: FURY LPAREN tipoVar IDENTIFICADOR JARVIS expr SEMI
    //               condition SEMI
    //               IDENTIFICADOR JARVIS expr
    //               RPAREN LBRACE statement* RBRACE   #StmtFor
    // =========================================================================
    @Override
    public String visitStmtFor(AvengerParser.StmtForContext ctx) {
        String javaType  = mapType(ctx.tipoVar().getText());
        String initVar   = ctx.IDENTIFICADOR(0).getText(); // variable del ciclo
        String initVal   = visit(ctx.expr(0));             // valor inicial
        String condition = visit(ctx.condition());
        String updateVar = ctx.IDENTIFICADOR(1).getText(); // variable de actualización
        String updateVal = visit(ctx.expr(1));             // valor de actualización

        javaCode.append(indent())
                .append("for (")
                .append(javaType).append(" ").append(initVar).append(" = ").append(initVal).append("; ")
                .append(condition).append("; ")
                .append(updateVar).append(" = ").append(updateVal)
                .append(") {\n");

        String outerScope = currentScope;
        currentScope = currentScope + ".for";
        indentLevel++;

        for (AvengerParser.StatementContext s : ctx.statement()) {
            visit(s);
        }

        indentLevel--;
        currentScope = outerScope;
        javaCode.append(indent()).append("}\n");
        return null;
    }

    // =========================================================================
    // Produccion 6: tipoVar IDENTIFICADOR LPAREN (param (COMMA param)*)? RPAREN
    //               LBRACE statement* RBRACE   #StmtFuncDecl
    // =========================================================================
    @Override
    public String visitStmtFuncDecl(AvengerParser.StmtFuncDeclContext ctx) {
        String returnType = mapType(ctx.tipoVar().getText());
        String funcName   = ctx.IDENTIFICADOR().getText();

        javaCode.append("\n").append(indent())
                .append("public static ").append(returnType).append(" ").append(funcName).append("(");

        appendParams(ctx.param());

        javaCode.append(") {\n");

        String outerScope = currentScope;
        currentScope = funcName;
        indentLevel++;

        for (AvengerParser.StatementContext s : ctx.statement()) {
            visit(s);
        }

        indentLevel--;
        currentScope = outerScope;
        javaCode.append(indent()).append("}\n");
        return null;
    }

    // =========================================================================
    // Produccion 7: BOB IDENTIFICADOR LPAREN (param (COMMA param)*)? RPAREN
    //               LBRACE statement* RBRACE   #StmtFuncDeclVoid
    // =========================================================================
    @Override
    public String visitStmtFuncDeclVoid(AvengerParser.StmtFuncDeclVoidContext ctx) {
        String funcName = ctx.IDENTIFICADOR().getText();

        javaCode.append("\n").append(indent())
                .append("public static void ").append(funcName).append("(");

        appendParams(ctx.param());

        javaCode.append(") {\n");

        String outerScope = currentScope;
        currentScope = funcName;
        indentLevel++;

        for (AvengerParser.StatementContext s : ctx.statement()) {
            visit(s);
        }

        indentLevel--;
        currentScope = outerScope;
        javaCode.append(indent()).append("}\n");
        return null;
    }

    // =========================================================================
    // Produccion 8: RETURN expr SEMI   #StmtReturn
    // =========================================================================
    @Override
    public String visitStmtReturn(AvengerParser.StmtReturnContext ctx) {
        String value = visit(ctx.expr());
        javaCode.append(indent()).append("return ").append(value).append(";\n");
        return null;
    }

    // =========================================================================
    // Produccion 9: GAMORA LPAREN IDENTIFICADOR RPAREN SEMI   #StmtRead
    // =========================================================================
    @Override
    public String visitStmtRead(AvengerParser.StmtReadContext ctx) {
        String name = ctx.IDENTIFICADOR().getText();
        String scannerMethod = "scanner.next()"; // Valor por defecto seguro

        // Buscamos la variable usando la misma lógica de compatibilidad que usó Samuel
        java.util.Optional<Variable> existing = symbolTable.stream()
                .filter(v -> v.getName().equals(name))
                .findFirst();

        if (existing.isPresent()) {
            switch (existing.get().getType()) {
                case "int":
                    scannerMethod = "scanner.nextInt()";
                    break;
                case "double":
                    scannerMethod = "scanner.nextDouble()";
                    break;
                case "boolean":
                    scannerMethod = "scanner.nextBoolean()";
                    break;
                default:
                    scannerMethod = "scanner.next()";
                    break;
            }
        }

        javaCode.append(indent())
                .append(name).append(" = ").append(scannerMethod).append(";\n");
        return null;
    }
    // =========================================================================
    // Produccion 10: NEBULA LPAREN expr RPAREN SEMI   #StmtWrite
    // =========================================================================
    @Override
    public String visitStmtWrite(AvengerParser.StmtWriteContext ctx) {
        String value = visit(ctx.expr());
        javaCode.append(indent())
                .append("System.out.println(").append(value).append(");\n");
        return null;
    }
    // =========================================================================
    // Produccion 11: RECRUIT STRING_ROGERS SEMI   #StmtImport
    // =========================================================================
    @Override
    public String visitStmtImport(AvengerParser.StmtImportContext ctx) {
        // STRING_ROGERS incluye comillas — se eliminan para obtener la ruta del import
        String raw        = ctx.STRING_ROGERS().getText();
        String importPath = raw.substring(1, raw.length() - 1);
        // Los imports van al inicio — se insertan antes del contenido existente
        javaCode.insert(0, "import " + importPath + ";\n");
        return null;
    }
    // =========================================================================
    // Produccion 12: ASSEMBLE STRING_ROGERS SEMI   #StmtAssemble
    // =========================================================================
    @Override
    public String visitStmtAssemble(AvengerParser.StmtAssembleContext ctx) {
        // STRING_ROGERS incluye comillas — se eliminan para obtener la ruta del archivo
        String raw  = ctx.STRING_ROGERS().getText();
        String ruta = raw.substring(1, raw.length() - 1);

        // Normalizar la ruta para evitar duplicados por rutas equivalentes (ej: ./a.avng vs a.avng)
        String rutaNormalizada;
        try {
            rutaNormalizada = Paths.get(ruta).toRealPath().toString();
        } catch (IOException e) {
            // Si el archivo no existe, toRealPath falla — usamos la ruta absoluta para el mensaje de error
            rutaNormalizada = Paths.get(ruta).toAbsolutePath().toString();
        }

        // Verificar si este archivo ya fue ensamblado para evitar ciclos o duplicados
        if (archivosEnsamblados.contains(rutaNormalizada)) {
            javaCode.append(indent())
                    .append("// assemble \"").append(ruta).append("\" — ya ensamblado, se omite\n");
            return null;
        }

        // Marcar el archivo como ensamblado antes de procesarlo (previene ciclos A→B→A)
        archivosEnsamblados.add(rutaNormalizada);

        // Leer el contenido del archivo externo
        String contenido;
        try {
            contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        } catch (IOException e) {
            System.err.println("  [ASSEMBLE] Error: no se pudo leer el archivo \"" + ruta + "\"");
            javaCode.append(indent())
                    .append("// assemble \"").append(ruta).append("\" — archivo no encontrado\n");
            return null;
        }

        // Parsear el archivo externo con el mismo lexer y parser
        AvengerLexer  lexerExterno  = new AvengerLexer(CharStreams.fromString(contenido));
        AvengerParser parserExterno = new AvengerParser(new CommonTokenStream(lexerExterno));
        AvengerParser.ProgContext arbolExterno = parserExterno.prog();

        // Crear un visitor hijo que comparte la tabla de símbolos y el conjunto de ensamblados
        // para que las variables del archivo externo sean visibles en el scope actual
        EvalVisitor visitorHijo = new EvalVisitor(symbolTable, archivosEnsamblados, indentLevel, currentScope);
        visitorHijo.visitProg(arbolExterno);

        // Insertar el código del archivo externo en el punto donde se usó assemble
        javaCode.append(indent())
                .append("// --- inicio: ").append(ruta).append(" ---\n");
        javaCode.append(visitorHijo.getJavaCode());
        javaCode.append(indent())
                .append("// --- fin: ").append(ruta).append(" ---\n");

        System.out.println("  [ASSEMBLE] Ensamblado: \"" + ruta + "\"");
        return null;
    }
    // =========================================================================
    // Produccion 13: IDENTIFICADOR LPAREN (expr (COMMA expr)*)? RPAREN SEMI   #StmtFuncCall
    // =========================================================================
    @Override
    public String visitStmtFuncCall(AvengerParser.StmtFuncCallContext ctx) {
        String call = buildFuncCall(ctx.IDENTIFICADOR().getText(), ctx.expr());
        javaCode.append(indent()).append(call).append(";\n");
        return null;
    }

    // =========================================================================
    // tipoVar : STARK | BANNER | ROGERS | THOR   #TipoVar
    // =========================================================================
    @Override
    public String visitTipoVar(AvengerParser.TipoVarContext ctx) {
        return mapType(ctx.getText());
    }

    // =========================================================================
    // param : tipoVar IDENTIFICADOR
    // =========================================================================
    @Override
    public String visitParam(AvengerParser.ParamContext ctx) {
        String javaType = mapType(ctx.tipoVar().getText());
        String name     = ctx.IDENTIFICADOR().getText();
        return javaType + " " + name;
    }

    // =========================================================================
    // condition : expr (PARKER | ODIN | NOJARVIS | EQEQ) expr
    // =========================================================================
    @Override
    public String visitCondition(AvengerParser.ConditionContext ctx) {
        String left  = visit(ctx.expr(0));
        String op    = ctx.getChild(1).getText(); // el operador es el segundo hijo
        String right = visit(ctx.expr(1));
        return left + " " + op + " " + right;
    }

    // =========================================================================
    // alternativas de expr
    // =========================================================================

    // =========================================================================
    // Produccion 1: expr (MULT | DIV) expr   #ExprMulDiv
    // =========================================================================
    @Override
    public String visitExprMulDiv(AvengerParser.ExprMulDivContext ctx) {
        String left  = visit(ctx.expr(0));
        String op    = ctx.getChild(1).getText();
        String right = visit(ctx.expr(1));
        return left + " " + op + " " + right;
    }

    // =========================================================================
    // Produccion 2: expr (PLUS | MINUS) expr   #ExprSumResta
    // =========================================================================
    @Override
    public String visitExprSumResta(AvengerParser.ExprSumRestaContext ctx) {
        String left  = visit(ctx.expr(0));
        String op    = ctx.getChild(1).getText();
        String right = visit(ctx.expr(1));
        return left + " " + op + " " + right;
    }

    // =========================================================================
    // Produccion 3: primary   #ExprPrimary
    // =========================================================================
    @Override
    public String visitExprPrimary(AvengerParser.ExprPrimaryContext ctx) {
        return visit(ctx.primary());
    }

    // =========================================================================
    // alternativas de primary
    // =========================================================================

    // =========================================================================
    // Produccion 1: MINUS primary   #PrimaryNegativo
    // =========================================================================
    @Override
    public String visitPrimaryNegativo(AvengerParser.PrimaryNegativoContext ctx) {
        return "-" + visit(ctx.primary());
    }

    // =========================================================================
    // Produccion 2: LPAREN expr RPAREN   #PrimaryAgrupado
    // =========================================================================
    @Override
    public String visitPrimaryAgrupado(AvengerParser.PrimaryAgrupadoContext ctx) {
        return "(" + visit(ctx.expr()) + ")";
    }

    // =========================================================================
    // Produccion 3: IDENTIFICADOR LPAREN (expr (COMMA expr)*)? RPAREN   #PrimaryFuncCall
    // =========================================================================
    @Override
    public String visitPrimaryFuncCall(AvengerParser.PrimaryFuncCallContext ctx) {
        return buildFuncCall(ctx.IDENTIFICADOR().getText(), ctx.expr());
    }

    // =========================================================================
    // Produccion 4: IDENTIFICADOR   #PrimaryId
    // =========================================================================
    @Override
    public String visitPrimaryId(AvengerParser.PrimaryIdContext ctx) {
        return ctx.IDENTIFICADOR().getText();
    }

    // =========================================================================
    // Produccion 5: NUMERO_STARK   #PrimaryEntero
    // =========================================================================
    @Override
    public String visitPrimaryEntero(AvengerParser.PrimaryEnteroContext ctx) {
        return ctx.NUMERO_STARK().getText();
    }

    // =========================================================================
    // Produccion 6: NUMERO_BANNER   #PrimaryFlotante
    // =========================================================================
    @Override
    public String visitPrimaryFlotante(AvengerParser.PrimaryFlotanteContext ctx) {
        return ctx.NUMERO_BANNER().getText();
    }

    // =========================================================================
    // Produccion 7: STRING_ROGERS   #PrimaryCadena
    // =========================================================================
    @Override
    public String visitPrimaryCadena(AvengerParser.PrimaryCadenaContext ctx) {
        // STRING_ROGERS ya incluye las comillas — es un literal String válido en Java
        return ctx.STRING_ROGERS().getText();
    }

    // =========================================================================
    // Produccion 8: BOOL_THOR   #PrimaryBooleano
    // =========================================================================
    @Override
    public String visitPrimaryBooleano(AvengerParser.PrimaryBooleanoContext ctx) {
        // Avenger: TRUE/FALSE → Java: true/false (minúsculas)
        return ctx.BOOL_THOR().getText().toLowerCase();
    }
}