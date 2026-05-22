package ide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class IdeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        String os = System.getProperty("os.name", "").toLowerCase();
        try {
            if (os.contains("win")) {
                // Works reliably from .bat on Windows (Desktop.getDesktop() doesn't)
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "http://localhost:8080"});
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"open", "http://localhost:8080"});
            } else {
                Runtime.getRuntime().exec(new String[]{"xdg-open", "http://localhost:8080"});
            }
        } catch (Exception ignored) {}
    }
}
