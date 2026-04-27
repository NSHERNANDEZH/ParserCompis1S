package example;

import antlr.AvengerBaseVisitor;
import antlr.AvengerParser;
import antlr.AvengerParser.ProgContext;

import java.util.ArrayList;
import java.util.List;

public class EvalVisitor extends AvengerBaseVisitor<String> {

    // ── Salidas ───────────────────────────────────────────────────────────────
    private final StringBuilder javaCode = new StringBuilder();
    // ... resto de tu código ...
    private final List<Variable> symbolTable = new ArrayList<>();
    private int indentLevel = 0;
    private String currentScope = "global";

    // ── Accesores públicos ────────────────────────────────────────────────────
    public String getJavaCode()            { return javaCode.toString(); }
    public List<Variable> getSymbolTable() { return symbolTable; }

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
    // PRODUCCIONES — pegar aquí los métodos @Override de cada integrante
    // =========================================================================
    // =========================================================================
    // prog : statement* EOF
    // =========================================================================
    @Override
    public String visitProg(ProgContext ctx) {
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

    // Produccion 1: tipoVar IDENTIFICADOR JARVIS expr SEMI   #StmtVarDecl
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

    // Produccion 2: IDENTIFICADOR JARVIS expr SEMI   #StmtAssign
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

    // Produccion 4: LOKI LPAREN condition RPAREN LBRACE statement* RBRACE   #StmtWhile
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
}