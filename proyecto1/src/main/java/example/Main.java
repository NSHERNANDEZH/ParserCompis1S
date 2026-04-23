package example;


import antlr.*;
import org.antlr.v4.runtime.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    private static final String RESET   = "\u001B[0m";
    private static final String BOLD    = "\u001B[1m";
    private static final String CYAN    = "\u001B[36m";
    private static final String GREEN   = "\u001B[32m";
    private static final String YELLOW  = "\u001B[33m";
    private static final String RED     = "\u001B[31m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String BLUE    = "\u001B[34m";
    private static final String WHITE   = "\u001B[37m";

    public static void main(String[] args) {

        printBanner();

        String rutaArchivo;

        if (args.length >= 1) {
            rutaArchivo = args[0];
        } else {
            System.out.print(CYAN + BOLD + "   Ingresá el nombre del archivo: " + RESET);
            Scanner scanner = new Scanner(System.in);
            rutaArchivo = scanner.nextLine().trim();

            if (!Files.exists(Paths.get(rutaArchivo))) {
                rutaArchivo = "proyecto1/testing/" + rutaArchivo;
            }
        }

        String codigoFuente;
        try {
            codigoFuente = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        } catch (IOException e) {
            printError("No se pudo leer el archivo: " + rutaArchivo);
            System.exit(1);
            return;
        }

        System.out.println(CYAN + BOLD + "  Archivo: " + RESET + WHITE + rutaArchivo + RESET);
        System.out.println(CYAN + "  ──────────────────────────────────────────────────" + RESET);
        System.out.println();

        CharStream input = CharStreams.fromString(codigoFuente);
        AvengerLexer lexer = new AvengerLexer(input);

        LexerErrorListener errorListener = new LexerErrorListener();
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();

        printTokenTable(tokens, lexer);
        printSummary(tokens, errorListener.getErrorCount());

        AvengerParser parser = new AvengerParser(tokenStream);

        ParseTree tree =  parser.prog();

        //Mostrar traduccion a codigo JAVA
        EvalVisitor evalVisitor = new EvalVisitor();
        evalVisitor.visit(tree);

        // Después de evalVisitor.visit(tree);
        System.out.println(GREEN + BOLD + "\n  ── CÓDIGO JAVA GENERADO ──\n" + RESET);
        System.out.println(evalVisitor.getJavaCode());

        System.out.println(CYAN + BOLD + "\n  ── TABLA DE SÍMBOLOS ──\n" + RESET);
        for (Variable v : evalVisitor.getSymbolTable()) {
            System.out.println("  " + v);
        }
    }

    private static void printTokenTable(List<Token> tokens, AvengerLexer lexer) {
        System.out.println(BOLD + CYAN
                + "  ┌──────┬──────────────────────────┬──────────────────────────────┬──────┬────────┐"
                + RESET);
        System.out.printf(BOLD + CYAN + "  │ %-4s │ %-24s │ %-28s │ %-4s │ %-6s │%n" + RESET,
                "#", "TOKEN (Tipo)", "LEXEMA (Valor)", "Línea", "Col");
        System.out.println(BOLD + CYAN
                + "  ├──────┼──────────────────────────┼──────────────────────────────┼──────┼────────┤"
                + RESET);

        int contador = 0;
        for (Token token : tokens) {
            if (token.getType() == Token.EOF) continue;
            contador++;

            String tipoNombre = lexer.getVocabulary().getSymbolicName(token.getType());
            String lexema = token.getText()
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");

            int linea   = token.getLine();
            int columna = token.getCharPositionInLine();
            String color = getColorForToken(tipoNombre);

            if (tipoNombre == null)   tipoNombre = "DESCONOCIDO";
            if (lexema.length() > 28) lexema = lexema.substring(0, 25) + "...";

            System.out.printf(color + "  │ %-4d │ %-24s │ %-28s │ %-4d │ %-6d │%n" + RESET,
                    contador, tipoNombre, lexema, linea, columna);
        }

        System.out.println(BOLD + CYAN
                + "  └──────┴──────────────────────────┴──────────────────────────────┴──────┴────────┘"
                + RESET);
        System.out.println();
    }

    private static String getColorForToken(String tipo) {
        if (tipo == null) return RED;

        if (tipo.equals("STARK") || tipo.equals("BANNER") ||
                tipo.equals("ROGERS") || tipo.equals("THOR") || tipo.equals("BOB"))
            return MAGENTA;

        else if (tipo.equals("VISION") || tipo.equals("WANDA") ||
                tipo.equals("LOKI") || tipo.equals("FURY"))
            return BLUE;

        else if (tipo.equals("JARVIS") || tipo.equals("PARKER") ||
                tipo.equals("ODIN") || tipo.equals("NOJARVIS"))
            return YELLOW;

        else if (tipo.equals("NUMERO_STARK") || tipo.equals("NUMERO_BANNER") ||
                tipo.equals("STRING_ROGERS") || tipo.equals("BOOL_THOR"))
            return GREEN;

        else if (tipo.equals("IDENTIFICADOR"))
            return WHITE;

        else
            return CYAN;
    }

    private static void printSummary(List<Token> tokens, int errores) {
        long totalTokens = tokens.stream()
                .filter(t -> t.getType() != Token.EOF)
                .count();

        System.out.println(BOLD + CYAN + "  ╔══════════════════════════════════╗" + RESET);
        System.out.println(BOLD + CYAN + "  ║       RESUMEN DEL ANÁLISIS       ║" + RESET);
        System.out.println(BOLD + CYAN + "  ╠══════════════════════════════════╣" + RESET);

        System.out.printf(BOLD + CYAN + "  ║" + RESET
                + GREEN + "  ✔ Tokens encontrados : %-9d" + RESET
                + BOLD + CYAN + "║%n" + RESET, totalTokens);

        System.out.printf(BOLD + CYAN + "  ║" + RESET
                        + (errores > 0 ? RED : GREEN)
                        + "  %s Errores léxicos   : %-9d" + RESET
                        + BOLD + CYAN + "║%n" + RESET,
                errores > 0 ? "✘" : "✔", errores);

        System.out.println(BOLD + CYAN + "  ╚══════════════════════════════════╝" + RESET);
        System.out.println();

        if (errores == 0)
            System.out.println(GREEN + BOLD + "   Análisis léxico completado sin errores." + RESET);
        else
            System.out.println(RED + BOLD
                    + "   Se encontraron " + errores + " error(es) léxico(s). Revisá el código fuente."
                    + RESET);

        System.out.println();
    }

    private static void printError(String mensaje) {
        System.out.println(RED + BOLD + "\n   ERROR: " + mensaje + RESET + "\n");
    }

    private static void printBanner() {
        System.out.println();
        System.out.println(BOLD + CYAN + "  ╔══════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BOLD + CYAN + "  ║" + RESET + MAGENTA + BOLD
                + "           ANALIZADOR LÉXICO  -  AvengerScript            "
                + RESET + BOLD + CYAN + "║" + RESET);
        System.out.println(BOLD + CYAN + "  ║" + RESET + WHITE
                + "         Compiladores · Universidad Rafael Landívar       "
                + RESET + BOLD + CYAN + "║" + RESET);
        System.out.println(BOLD + CYAN + "  ╚══════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }
}