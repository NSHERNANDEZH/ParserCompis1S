package example;

import antlr.*;
import java.util.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import antlr.AvengerLexer;
import antlr.AvengerParser;

public class SemanticVisitor extends AvengerBaseVisitor<String> {

    private final List<String> semanticErrors = new ArrayList<>();
    // scope stack: cada nivel es un mapa nombre→tipo
    private final Deque<Map<String, String>> scopeStack = new ArrayDeque<>();
    // funciones declaradas: nombre → tipo retorno
    private final Map<String, String> functionTable = new HashMap<>();

    public SemanticVisitor() {
        scopeStack.push(new HashMap<>()); // scope global
    }

    public List<String> getSemanticErrors() { return semanticErrors; }
    public boolean hasErrors() { return !semanticErrors.isEmpty(); }

    private void declare(String name, String type) {
        scopeStack.peek().put(name, type);
    }

    private String lookupType(String name) {
        for (Map<String, String> scope : scopeStack) {
            if (scope.containsKey(name)) return scope.get(name);
        }
        return null;
    }

    private void pushScope() { scopeStack.push(new HashMap<>()); }
    private void popScope()  { scopeStack.pop(); }

    @Override
    public String visitStmtVarDecl(AvengerParser.StmtVarDeclContext ctx) {
        String javaType = mapType(ctx.tipoVar().getText());
        String name     = ctx.IDENTIFICADOR().getText();
        String exprType = visit(ctx.expr());

        // Verificar redeclaración en el mismo scope
        if (scopeStack.peek().containsKey(name)) {
            semanticErrors.add("Error Semántico [línea " + ctx.getStart().getLine() + "]: " +
                    "Variable '" + name + "' ya fue declarada en este scope.");
        }

        // Verificar compatibilidad de tipos
        if (exprType != null && !exprType.equals("unknown") && !javaType.equals(exprType)) {
            // Permitir int→double (widening)
            boolean ok = javaType.equals("double") && exprType.equals("int");
            if (!ok) {
                semanticErrors.add("Error Semántico [línea " + ctx.getStart().getLine() + "]: " +
                        "Tipo incompatible: se esperaba '" + javaType + "' pero se obtuvo '" + exprType + "' para '" + name + "'.");
            }
        }
        declare(name, javaType);
        return javaType;
    }

    @Override
    public String visitStmtAssign(AvengerParser.StmtAssignContext ctx) {
        String name     = ctx.IDENTIFICADOR().getText();
        String declared = lookupType(name);

        if (declared == null) {
            semanticErrors.add("Error Semántico [línea " + ctx.getStart().getLine() + "]: " +
                    "Variable '" + name + "' no fue declarada.");
            return null;
        }
        String exprType = visit(ctx.expr());
        if (exprType != null && !exprType.equals("unknown") && !declared.equals(exprType)) {
            boolean ok = declared.equals("double") && exprType.equals("int");
            if (!ok) {
                semanticErrors.add("Error Semántico [línea " + ctx.getStart().getLine() + "]: " +
                        "Tipo incompatible en asignación a '" + name + "': se esperaba '" + declared + "' pero se obtuvo '" + exprType + "'.");
            }
        }
        return declared;
    }

    @Override
    public String visitStmtFuncDecl(AvengerParser.StmtFuncDeclContext ctx) {
        String returnType = mapType(ctx.tipoVar().getText());
        String funcName   = ctx.IDENTIFICADOR().getText();
        functionTable.put(funcName, returnType);
        pushScope();
        for (AvengerParser.ParamContext p : ctx.param()) {
            declare(p.IDENTIFICADOR().getText(), mapType(p.tipoVar().getText()));
        }
        visitChildren(ctx);  // visita el cuerpo
        popScope();
        return returnType;
    }

    @Override
    public String visitStmtFuncDeclVoid(AvengerParser.StmtFuncDeclVoidContext ctx) {
        functionTable.put(ctx.IDENTIFICADOR().getText(), "void");
        pushScope();
        for (AvengerParser.ParamContext p : ctx.param()) {
            declare(p.IDENTIFICADOR().getText(), mapType(p.tipoVar().getText()));
        }
        visitChildren(ctx);
        popScope();
        return "void";
    }

    @Override
    public String visitStmtIf(AvengerParser.StmtIfContext ctx) {
        visit(ctx.condition());
        pushScope(); visitChildren(ctx); popScope();
        return null;
    }

    @Override
    public String visitStmtWhile(AvengerParser.StmtWhileContext ctx) {
        visit(ctx.condition());
        pushScope(); visitChildren(ctx); popScope();
        return null;
    }

    @Override
    public String visitStmtFor(AvengerParser.StmtForContext ctx) {
        pushScope();

        // Declarar la variable de inicialización ANTES de visitar condición y cuerpo
        String javaType = mapType(ctx.tipoVar().getText());
        String initVar  = ctx.IDENTIFICADOR(0).getText();
        declare(initVar, javaType);

        // Visitar init expr, condición, update expr y body con la variable ya declarada
        visit(ctx.expr(0));
        visit(ctx.condition());
        visit(ctx.expr(1));
        for (AvengerParser.StatementContext s : ctx.statement()) visit(s);

        popScope();
        return null;
    }

    // ── Expresiones: retornan tipo inferido ──────────────────────────────────

    @Override
    public String visitPrimaryId(AvengerParser.PrimaryIdContext ctx) {
        String name = ctx.IDENTIFICADOR().getText();
        String type = lookupType(name);
        if (type == null) {
            semanticErrors.add("Error Semántico [línea " + ctx.getStart().getLine() + "]: " +
                    "Variable '" + name + "' no declarada.");
            return "unknown";
        }
        return type;
    }

    @Override
    public String visitPrimaryEntero(AvengerParser.PrimaryEnteroContext ctx)    { return "int"; }
    @Override
    public String visitPrimaryFlotante(AvengerParser.PrimaryFlotanteContext ctx) { return "double"; }
    @Override
    public String visitPrimaryCadena(AvengerParser.PrimaryCadenaContext ctx)    { return "String"; }
    @Override
    public String visitPrimaryBooleano(AvengerParser.PrimaryBooleanoContext ctx) { return "boolean"; }

    @Override
    public String visitExprMulDiv(AvengerParser.ExprMulDivContext ctx) {
        String l = visit(ctx.expr(0)), r = visit(ctx.expr(1));
        if ("double".equals(l) || "double".equals(r)) return "double";
        return "int";
    }

    @Override
    public String visitExprSumResta(AvengerParser.ExprSumRestaContext ctx) {
        String l = visit(ctx.expr(0)), r = visit(ctx.expr(1));
        if ("String".equals(l) || "String".equals(r)) return "String";
        if ("double".equals(l) || "double".equals(r)) return "double";
        return "int";
    }

    @Override
    public String visitPrimaryFuncCall(AvengerParser.PrimaryFuncCallContext ctx) {
        String name = ctx.IDENTIFICADOR().getText();
        if (!functionTable.containsKey(name)) {
            semanticErrors.add("Error Semántico [línea " + ctx.getStart().getLine() + "]: " +
                    "Función '" + name + "' no declarada.");
            return "unknown";
        }
        return functionTable.getOrDefault(name, "unknown");
    }

    @Override
    public String visitStmtAssemble(AvengerParser.StmtAssembleContext ctx) {
        String raw  = ctx.STRING_ROGERS().getText();
        String ruta = raw.substring(1, raw.length() - 1);

        String contenido;
        try {
            contenido = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(ruta)));
        } catch (java.io.IOException e) {
            semanticErrors.add("Error Semántico [línea " + ctx.getStart().getLine() + "]: " +
                    "No se pudo leer el archivo ensamblado: \"" + ruta + "\"");
            return null;
        }

        AvengerLexer  lexerExterno  = new AvengerLexer(CharStreams.fromString(contenido));
        AvengerParser parserExterno = new AvengerParser(new CommonTokenStream(lexerExterno));
        AvengerParser.ProgContext arbolExterno = parserExterno.prog();

        // Pre-registrar todas las funciones del archivo externo ANTES de visitar el cuerpo
        for (AvengerParser.StatementContext s : arbolExterno.statement()) {
            if (s instanceof AvengerParser.StmtFuncDeclContext) {
                AvengerParser.StmtFuncDeclContext f = (AvengerParser.StmtFuncDeclContext) s;
                functionTable.put(f.IDENTIFICADOR().getText(), mapType(f.tipoVar().getText()));
            } else if (s instanceof AvengerParser.StmtFuncDeclVoidContext) {
                AvengerParser.StmtFuncDeclVoidContext f = (AvengerParser.StmtFuncDeclVoidContext) s;
                functionTable.put(f.IDENTIFICADOR().getText(), "void");
            }
        }

        // Visitar el árbol externo para detectar errores semánticos dentro del módulo
        visit(arbolExterno);
        return null;
    }

    @Override
    public String visitExprPrimary(AvengerParser.ExprPrimaryContext ctx) { return visit(ctx.primary()); }
    @Override
    public String visitPrimaryNegativo(AvengerParser.PrimaryNegativoContext ctx) { return visit(ctx.primary()); }
    @Override
    public String visitPrimaryAgrupado(AvengerParser.PrimaryAgrupadoContext ctx) { return visit(ctx.expr()); }

    private String mapType(String t) {
        switch (t) {
            case "stark":  return "int";
            case "banner": return "double";
            case "rogers": return "String";
            case "thor":   return "boolean";
            default:       return t;
        }
    }
}