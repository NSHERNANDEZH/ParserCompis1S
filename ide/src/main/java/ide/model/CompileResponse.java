package ide.model;

import java.util.List;

public class CompileResponse {
    private String javaCode;
    private List<String> errors;
    private List<TokenInfo> tokens;

    public CompileResponse(String javaCode, List<String> errors, List<TokenInfo> tokens) {
        this.javaCode = javaCode;
        this.errors = errors;
        this.tokens = tokens;
    }

    public String getJavaCode() { return javaCode; }
    public List<String> getErrors() { return errors; }
    public List<TokenInfo> getTokens() { return tokens; }
}
