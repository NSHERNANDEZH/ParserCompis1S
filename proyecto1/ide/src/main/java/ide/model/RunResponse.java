package ide.model;

import java.util.List;

public class RunResponse {
    private boolean success;
    private String javaCode;
    private String output;
    private String error;
    private List<String> errors;
    private List<TokenInfo> tokens;

    public RunResponse(boolean success, String javaCode, String output,
                       String error, List<String> errors, List<TokenInfo> tokens) {
        this.success = success;
        this.javaCode = javaCode;
        this.output = output;
        this.error = error;
        this.errors = errors;
        this.tokens = tokens;
    }

    public boolean isSuccess() { return success; }
    public String getJavaCode() { return javaCode; }
    public String getOutput() { return output; }
    public String getError() { return error; }
    public List<String> getErrors() { return errors; }
    public List<TokenInfo> getTokens() { return tokens; }
}
