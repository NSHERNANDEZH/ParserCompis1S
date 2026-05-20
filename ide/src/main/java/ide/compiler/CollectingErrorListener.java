package ide.compiler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class CollectingErrorListener extends BaseErrorListener {

    private final String errorType;
    private final List<String> errors = new ArrayList<>();

    public CollectingErrorListener(String errorType) {
        this.errorType = errorType;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg, RecognitionException e) {
        String symbol;
        if (offendingSymbol instanceof Token) {
            Token token = (Token) offendingSymbol;
            symbol = token.getType() == Token.EOF ? "<fin de archivo>" : token.getText();
        } else if (offendingSymbol != null) {
            symbol = offendingSymbol.toString();
        } else {
            symbol = "?";
        }

        String message = "LÉXICO".equals(errorType)
                ? String.format("ERROR LÉXICO [línea %d, col %d]: Símbolo no reconocido → '%s'", line, charPositionInLine, symbol)
                : String.format("ERROR SINTÁCTICO [línea %d, col %d]: Token inesperado → '%s'", line, charPositionInLine, symbol);

        errors.add(message);
    }

    public List<String> getErrors() { return errors; }
    public int getErrorCount() { return errors.size(); }
}
