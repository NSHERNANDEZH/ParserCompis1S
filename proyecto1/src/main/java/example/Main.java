package example;

import antlr.AvengerLexer;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * ╔══════════════════════════════════════════════════════════╗
 *  COMPILADORES - FASE I: ANALIZADOR LÉXICO
 *  Universidad Rafael Landívar
 *  Curso: Compiladores | Mgtr. Moises Alonso
 * ╚══════════════════════════════════════════════════════════╝
 *
 *  Descripción:
 *      Analizador léxico basado en ANTLR4 para el lenguaje
 *      "Avenger". Lee un archivo fuente, tokeniza su contenido
 *      y muestra el listado de tokens junto con errores léxicos.
 *
 *  Uso:
 *      Correr Main con el path del archivo fuente como argumento.
 *      Ejemplo: testing/prueba1.txt
 */
public class Main {

    // ═══════════════════════════════════════════
    //  COLORES ANSI para salida en consola
    // ═══════════════════════════════════════════
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

        // Si se pasó argumento, usarlo; si no, pedirlo por consola
        if (args.length >= 1) {
            rutaArchivo = args[0];
        } else {
            System.out.print(CYAN + BOLD + "  📂 Ingresá el nombre del archivo: " + RESET);
            Scanner scanner = new Scanner(System.in);
            rutaArchivo = scanner.nextLine().trim();
        }
        // ── Leer archivo ───────────────────────────────────────
        String codigoFuente;
        try {
            codigoFuente = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        } catch (IOException e) {
            printError("No se pudo leer el archivo: " + rutaArchivo);
            System.exit(1);
            return;
        }

        System.out.println(CYAN + BOLD + "  📄 Archivo: " + RESET + WHITE + rutaArchivo + RESET);
        System.out.println(CYAN + "  ──────────────────────────────────────────────────" + RESET);
        System.out.println();

        // ── Crear stream de entrada ────────────────────────────
        CharStream input = CharStreams.fromString(codigoFuente);

        // ── Instanciar el Lexer generado por ANTLR ────────────
        AvengerLexer lexer = new AvengerLexer(input);

        // ── Listener de errores léxicos personalizado ─────────
        LexerErrorListener errorListener = new LexerErrorListener();
        lexer.removeErrorListeners();           // quitar listener por defecto
        lexer.addErrorListener(errorListener);  // agregar el nuestro

        // ── Obtener todos los tokens ───────────────────────────
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();

        // ── Imprimir tabla de tokens ───────────────────────────
        printTokenTable(tokens, lexer);

        // ── Imprimir resumen ───────────────────────────────────
        printSummary(tokens, errorListener.getErrorCount());
    }

    // ═══════════════════════════════════════════════════════════
    //  Imprime la tabla de tokens encontrados
    // ═══════════════════════════════════════════════════════════
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

            // Ignorar EOF en la tabla
            if (token.getType() == Token.EOF) continue;

            contador++;

            String tipoNombre = lexer.getVocabulary().getSymbolicName(token.getType());
            String lexema     = token.getText()
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");
            int    linea      = token.getLine();
            int    columna    = token.getCharPositionInLine();

            // Color según categoría
            String color = getColorForToken(tipoNombre);

            // Truncar si el texto es muy largo
            if (tipoNombre == null)      tipoNombre = "DESCONOCIDO";
            if (lexema.length() > 28)    lexema = lexema.substring(0, 25) + "...";

            System.out.printf(color
                            + "  │ %-4d │ %-24s │ %-28s │ %-4d │ %-6d │%n" + RESET,
                    contador, tipoNombre, lexema, linea, columna);
        }

        System.out.println(BOLD + CYAN
                + "  └──────┴──────────────────────────┴──────────────────────────────┴──────┴────────┘"
                + RESET);
        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════
    //  Color ANSI según categoría del token
    // ═══════════════════════════════════════════════════════════
    private static String getColorForToken(String tipo) {
        if (tipo == null) return RED;

        if (tipo.equals("STARK") || tipo.equals("BANNER") ||
                tipo.equals("ROGERS") || tipo.equals("THOR") || tipo.equals("BOB")) {
            return MAGENTA;
        } else if (tipo.equals("VISION") || tipo.equals("WANDA") ||
                tipo.equals("LOKI") || tipo.equals("FURY")) {
            return BLUE;
        } else if (tipo.equals("JARVIS") || tipo.equals("PARKER") ||
                tipo.equals("ODIN") || tipo.equals("NOJARVIS")) {
            return YELLOW;
        } else if (tipo.equals("NUMERO_INT") || tipo.equals("NUMERO_FLOAT") ||
                tipo.equals("STRING") || tipo.equals("BOOL_VAL")) {
            return GREEN;
        } else if (tipo.equals("IDENTIFICADOR")) {
            return WHITE;
        } else {
            return CYAN;
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  Resumen final del análisis
    // ═══════════════════════════════════════════════════════════
    private static void printSummary(List<Token> tokens, int errores) {

        long totalTokens = tokens.stream()
                .filter(t -> t.getType() != Token.EOF)
                .count();

        System.out.println(BOLD + CYAN  + "  ╔══════════════════════════════════╗" + RESET);
        System.out.println(BOLD + CYAN  + "  ║       RESUMEN DEL ANÁLISIS       ║" + RESET);
        System.out.println(BOLD + CYAN  + "  ╠══════════════════════════════════╣" + RESET);
        System.out.printf (BOLD + CYAN  + "  ║" + RESET
                + GREEN  + "  ✔ Tokens encontrados : %-9d" + RESET
                + BOLD + CYAN + "║%n" + RESET, totalTokens);
        System.out.printf (BOLD + CYAN  + "  ║" + RESET
                        + (errores > 0 ? RED : GREEN)
                        + "  %s Errores léxicos   : %-9d" + RESET
                        + BOLD + CYAN + "║%n" + RESET,
                errores > 0 ? "✘" : "✔", errores);
        System.out.println(BOLD + CYAN  + "  ╚══════════════════════════════════╝" + RESET);
        System.out.println();

        if (errores == 0) {
            System.out.println(GREEN + BOLD + "  ✔ Análisis léxico completado sin errores." + RESET);
        } else {
            System.out.println(RED + BOLD
                    + "  ✘ Se encontraron " + errores + " error(es) léxico(s). Revisá el código fuente." + RESET);
        }
        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════
    //  Mensaje de error formateado
    // ═══════════════════════════════════════════════════════════
    private static void printError(String mensaje) {
        System.out.println(RED + BOLD + "\n  ✘ ERROR: " + mensaje + RESET + "\n");
    }

    // ═══════════════════════════════════════════════════════════
    //  Banner de bienvenida
    // ═══════════════════════════════════════════════════════════
    private static void printBanner() {
        System.out.println();
        System.out.println(BOLD + CYAN    + "  ╔══════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BOLD + CYAN    + "  ║" + RESET + MAGENTA + BOLD + "          ⚡  ANALIZADOR LÉXICO  -  AVENGER  ⚡           " + RESET + BOLD + CYAN + "║" + RESET);
        System.out.println(BOLD + CYAN    + "  ║" + RESET + WHITE         + "         Compiladores · Universidad Rafael Landívar       " + RESET + BOLD + CYAN + "║" + RESET);
        System.out.println(BOLD + CYAN    + "  ╚══════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }
}

// ═══════════════════════════════════════════════════════════════
//  Listener de errores léxicos personalizado
// ═══════════════════════════════════════════════════════════════
class LexerErrorListener extends BaseErrorListener {

    private static final String RED   = "\u001B[31m";
    private static final String BOLD  = "\u001B[1m";
    private static final String RESET = "\u001B[0m";

    private int errorCount = 0;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        errorCount++;
        System.out.printf(RED + BOLD
                        + "  ✘ ERROR LÉXICO [línea %d, col %d]: Símbolo no reconocido → '%s'%n"
                        + RESET,
                line, charPositionInLine,
                offendingSymbol != null ? offendingSymbol : "?");
    }

    public int getErrorCount() {
        return errorCount;
    }
}