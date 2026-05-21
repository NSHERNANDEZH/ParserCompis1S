package ide.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ide.model.CompileResponse;
import ide.service.CompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.concurrent.*;

@Component
public class TerminalWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private CompilerService compilerService;

    private final ObjectMapper mapper = new ObjectMapper();
    private final ConcurrentHashMap<String, Process>       processes  = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, OutputStream>  stdins     = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ExecutorService> executors = new ConcurrentHashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JsonNode msg = mapper.readTree(message.getPayload());
        String type  = msg.get("type").asText();

        switch (type) {
            case "run":  handleRun(session, msg.get("code").asText()); break;
            case "stdin": handleStdin(session, msg.get("data").asText()); break;
            case "kill": killSession(session); break;
        }
    }

    private void handleRun(WebSocketSession session, String code) {
        killSession(session); // matar proceso previo si existe

        CompileResponse compiled = compilerService.compile(code);

        send(session, "tokens", null, compiled.getTokens());
        send(session, "errors", null, compiled.getErrors());

        if (compiled.getJavaCode() == null) {
            send(session, "exit", "1", null);
            return;
        }

        send(session, "javaCode", compiled.getJavaCode(), null);

        try {
            Path tempDir  = Files.createTempDirectory("avenger_");
            Path javaFile = tempDir.resolve("Traduccion.java");
            Files.write(javaFile, compiled.getJavaCode().getBytes(StandardCharsets.UTF_8));

            String javacExe = compilerService.findJavaTool("javac");
            String javaExe  = compilerService.findJavaTool("java");

            // Compilar con javac
            Process javacProc = new ProcessBuilder(javacExe, "-encoding", "UTF-8", "Traduccion.java")
                    .directory(tempDir.toFile())
                    .redirectErrorStream(true)
                    .start();
            String javacOut = readFully(javacProc.getInputStream());
            javacProc.waitFor(30, TimeUnit.SECONDS);

            if (javacProc.exitValue() != 0) {
                send(session, "stderr", "Error de compilación Java:\n" + javacOut, null);
                send(session, "exit", "1", null);
                return;
            }

            // Ejecutar
            Process proc = new ProcessBuilder(javaExe, "-Dfile.encoding=UTF-8", "-cp", tempDir.toString(), "Traduccion")
                    .directory(tempDir.toFile())
                    .start();

            processes.put(session.getId(), proc);
            stdins.put(session.getId(), proc.getOutputStream());

            ExecutorService pool = Executors.newFixedThreadPool(2);
            executors.put(session.getId(), pool);

            // Thread: stream stdout al cliente en tiempo real
            pool.submit(() -> {
                try {
                    InputStreamReader isr = new InputStreamReader(proc.getInputStream(), StandardCharsets.UTF_8);
                    char[] buf = new char[256];
                    int n;
                    while ((n = isr.read(buf)) != -1) {
                        send(session, "stdout", new String(buf, 0, n), null);
                    }
                } catch (Exception ignored) {}
            });

            // Thread: esperar fin del proceso
            pool.submit(() -> {
                try {
                    boolean finished = proc.waitFor(30, TimeUnit.SECONDS);
                    if (!finished) {
                        proc.destroyForcibly();
                        send(session, "stderr", "\nTimeout: programa detenido (30s)", null);
                    }
                    // stderr
                    String err = readFully(proc.getErrorStream());
                    if (!err.isBlank()) send(session, "stderr", err, null);

                    int code2 = proc.exitValue();
                    send(session, "exit", String.valueOf(code2), null);
                } catch (Exception ignored) {
                } finally {
                    processes.remove(session.getId());
                    stdins.remove(session.getId());
                }
            });

            pool.shutdown();

        } catch (Exception e) {
            send(session, "stderr", "Error del servidor: " + e.getMessage(), null);
            send(session, "exit", "1", null);
        }
    }

    private void handleStdin(WebSocketSession session, String data) {
        OutputStream out = stdins.get(session.getId());
        if (out != null) {
            try {
                out.write((data + "\n").getBytes(StandardCharsets.UTF_8));
                out.flush();
            } catch (IOException ignored) {}
        }
    }

    private void killSession(WebSocketSession session) {
        Process proc = processes.remove(session.getId());
        if (proc != null) proc.destroyForcibly();
        stdins.remove(session.getId());
        ExecutorService pool = executors.remove(session.getId());
        if (pool != null) pool.shutdownNow();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        killSession(session);
    }

    private void send(WebSocketSession session, String type, Object data, Object extra) {
        try {
            ObjectNode node = mapper.createObjectNode();
            node.put("type", type);
            if (data != null)  node.set("data",  mapper.valueToTree(data));
            if (extra != null) node.set("extra", mapper.valueToTree(extra));
            synchronized (session) {
                if (session.isOpen())
                    session.sendMessage(new TextMessage(mapper.writeValueAsString(node)));
            }
        } catch (Exception ignored) {}
    }

    private String readFully(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = r.readLine()) != null) sb.append(line).append('\n');
        }
        return sb.toString();
    }
}
