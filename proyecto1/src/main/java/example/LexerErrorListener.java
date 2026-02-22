package example;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

//
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
                        + "   ERROR LÉXICO [línea %d, col %d]: Símbolo no reconocido → '%s'%n"
                        + RESET,
                line, charPositionInLine,
                offendingSymbol != null ? offendingSymbol : "?");
    }

    public int getErrorCount() { return errorCount; }
}