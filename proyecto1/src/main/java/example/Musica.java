package example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Clase encargada de reproducir música de fondo
 * usando Windows Script Host (wscript + WMPlayer)
 */
public class Musica {

    private static final String CYAN  = "\u001B[36m";
    private static final String BOLD  = "\u001B[1m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void play(String rutaAudio) {
        try {
            String rutaAbsoluta = Paths.get(rutaAudio)
                    .toAbsolutePath()
                    .toString()
                    .replace("\\", "\\\\");

            String script =
                    "var s = new ActiveXObject('WMPlayer.OCX');" +
                            "s.URL = '" + rutaAbsoluta + "';" +
                            "s.controls.play();" +
                            "WScript.Sleep(300000);";

            java.nio.file.Path tempScript = Files.createTempFile("avenger_music", ".js");
            Files.write(tempScript, script.getBytes());

            new ProcessBuilder("wscript", tempScript.toAbsolutePath().toString())
                    .start();

            System.out.println(CYAN + BOLD + "   *Música de los Avengers activada* " + RESET);

        } catch (IOException e) {
            System.out.println(YELLOW + "  No se pudo reproducir música: " + e.getMessage() + RESET);
        }
    }

    public static void stop() {
        try {
            // Mata el proceso wscript al terminar
            new ProcessBuilder("taskkill", "/F", "/IM", "wscript.exe")
                    .start();
        } catch (IOException e) {
            // Silencioso si no hay proceso que matar
        }
    }
}