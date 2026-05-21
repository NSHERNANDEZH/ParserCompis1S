package ide.service;

import antlr.AvengerLexer;
import antlr.AvengerParser;
import example.EvalVisitor;
import example.SemanticVisitor;
import ide.compiler.CollectingErrorListener;
import ide.model.CompileResponse;
import ide.model.RunResponse;
import ide.model.TokenInfo;
import org.antlr.v4.runtime.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CompilerService {

    public CompileResponse compile(String sourceCode) {
        List<String> errors = new ArrayList<>();
        List<TokenInfo> tokens = new ArrayList<>();

        CharStream input = CharStreams.fromString(sourceCode);
        AvengerLexer lexer = new AvengerLexer(input);
        lexer.removeErrorListeners();

        CollectingErrorListener lexerListener = new CollectingErrorListener("LÉXICO");
        lexer.addErrorListener(lexerListener);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        tokenStream.fill();

        for (Token token : tokenStream.getTokens()) {
            if (token.getType() != Token.EOF) {
                String typeName = AvengerLexer.VOCABULARY.getSymbolicName(token.getType());
                if (typeName == null) typeName = String.valueOf(token.getType());
                tokens.add(new TokenInfo(typeName, token.getText(), token.getLine(), token.getCharPositionInLine()));
            }
        }

        if (lexerListener.getErrorCount() > 0) {
            errors.addAll(lexerListener.getErrors());
            return new CompileResponse(null, errors, tokens);
        }

        AvengerParser parser = new AvengerParser(tokenStream);
        parser.removeErrorListeners();

        CollectingErrorListener parserListener = new CollectingErrorListener("SINTÁCTICO");
        parser.addErrorListener(parserListener);

        AvengerParser.ProgContext tree = parser.prog();

        if (parserListener.getErrorCount() > 0) {
            errors.addAll(parserListener.getErrors());
            return new CompileResponse(null, errors, tokens);
        }

        try {
            // Análisis semántico (igual que en Main.java)
            SemanticVisitor semanticVisitor = new SemanticVisitor();
            semanticVisitor.visit(tree);
            if (semanticVisitor.hasErrors()) {
                errors.addAll(semanticVisitor.getSemanticErrors());
                return new CompileResponse(null, errors, tokens);
            }

            EvalVisitor visitor = new EvalVisitor();
            visitor.visit(tree);
            String javaCode = visitor.getJavaCode();
            return new CompileResponse(javaCode, errors, tokens);
        } catch (Exception e) {
            errors.add("ERROR DE GENERACIÓN: " + e.getMessage());
            return new CompileResponse(null, errors, tokens);
        }
    }

    public RunResponse run(String sourceCode, String stdin) {
        CompileResponse compiled = compile(sourceCode);

        if (compiled.getJavaCode() == null) {
            return new RunResponse(false, null, null,
                    "Errores de compilación:\n" + String.join("\n", compiled.getErrors()),
                    compiled.getErrors(), compiled.getTokens());
        }

        try {
            Path tempDir = Files.createTempDirectory("avenger_ide_");
            Path javaFile = tempDir.resolve("Traduccion.java");
            Files.write(javaFile, compiled.getJavaCode().getBytes(StandardCharsets.UTF_8));

            // Encontrar javac en el JDK actual
            String javacExe = findJavaTool("javac");
            String javaExe  = findJavaTool("java");

            // Compilar con javac
            ProcessBuilder javacBuilder = new ProcessBuilder(javacExe, "-encoding", "UTF-8", javaFile.getFileName().toString());
            javacBuilder.directory(tempDir.toFile());
            Process javacProcess = javacBuilder.start();

            String javacError = readStream(javacProcess.getErrorStream());
            boolean javacDone = javacProcess.waitFor(30, TimeUnit.SECONDS);

            if (!javacDone) {
                javacProcess.destroyForcibly();
                return new RunResponse(false, compiled.getJavaCode(), null,
                        "Timeout al compilar Java (>30s)", compiled.getErrors(), compiled.getTokens());
            }

            if (javacProcess.exitValue() != 0) {
                return new RunResponse(false, compiled.getJavaCode(), null,
                        "Error de compilación Java:\n" + javacError,
                        compiled.getErrors(), compiled.getTokens());
            }

            // Ejecutar con java
            ProcessBuilder javaBuilder = new ProcessBuilder(javaExe, "-Dfile.encoding=UTF-8", "-cp", tempDir.toString(), "Traduccion");
            javaBuilder.directory(tempDir.toFile());
            Process javaProcess = javaBuilder.start();

            try (var procIn = javaProcess.getOutputStream()) {
                if (stdin != null && !stdin.isEmpty()) {
                    procIn.write((stdin + "\n").getBytes(StandardCharsets.UTF_8));
                }
            }

            String output   = readStream(javaProcess.getInputStream());
            String runError = readStream(javaProcess.getErrorStream());
            boolean done    = javaProcess.waitFor(10, TimeUnit.SECONDS);

            if (!done) {
                javaProcess.destroyForcibly();
                return new RunResponse(false, compiled.getJavaCode(), output,
                        "Timeout: programa detuvo después de 10s — posible bucle infinito",
                        compiled.getErrors(), compiled.getTokens());
            }

            boolean ok = javaProcess.exitValue() == 0;
            String errorMsg = null;
            if (!ok) {
                errorMsg = runError.contains("NoSuchElementException")
                    ? "El programa usa 'gamora' para leer entrada pero no se proporcionó stdin.\nEscribí los valores en el campo de entrada antes de ejecutar."
                    : runError;
            }
            return new RunResponse(ok, compiled.getJavaCode(), output,
                    errorMsg, compiled.getErrors(), compiled.getTokens());

        } catch (IOException | InterruptedException e) {
            return new RunResponse(false, compiled.getJavaCode(), null,
                    "Error del servidor: " + e.getMessage(),
                    compiled.getErrors(), compiled.getTokens());
        }
    }

    public String findJavaTool(String tool) {
        String javaHome = System.getProperty("java.home");
        boolean isWindows = System.getProperty("os.name", "").toLowerCase().contains("win");
        String ext = isWindows ? ".exe" : "";

        // JDK 9+: java.home apunta al JDK directamente
        Path toolPath = Paths.get(javaHome, "bin", tool + ext);
        if (Files.exists(toolPath)) return toolPath.toString();

        // JDK 8: java.home apunta al JRE dentro del JDK
        Path parent = Paths.get(javaHome).getParent();
        if (parent != null) {
            toolPath = parent.resolve("bin" + File.separator + tool + ext);
            if (Files.exists(toolPath)) return toolPath.toString();
        }

        return tool + ext; // fallback al PATH
    }

    private String readStream(java.io.InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }
        return sb.toString();
    }
}
