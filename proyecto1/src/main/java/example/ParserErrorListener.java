package example;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;


//  Listener de errores sintácticos personalizado
class ParserErrorListener extends BaseErrorListener {

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

        String simbolo;
        if (offendingSymbol instanceof Token) {
            Token token = (Token) offendingSymbol;
            if (token.getType() == Token.EOF) {
                simbolo = "<fin de archivo>";
            } else {
                simbolo = token.getText();
            }
        } else if (offendingSymbol != null) {
            simbolo = offendingSymbol.toString();
        } else {
            simbolo = "?";
        }

        System.out.printf(RED + BOLD
                        + "   ERROR SINTÁCTICO [línea %d, col %d]: Token inesperado → '%s'%n"
                        + RESET,
                line, charPositionInLine, simbolo);
    }

    public int getErrorCount() { return errorCount; }
}