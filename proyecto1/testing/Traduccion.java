public class Traduccion {
    public static void main(String[] args) {
        int x = 10;
        double temperatura = 36.6;
        String nombre = "Tony";
        boolean activo = true;
        for (int i = 0; i < 5; i = i + 1) {
            int doble = i * 2;
        }
        if (x > 0) {
            int positivo = x * 10;
        } else {
            int negativo = x * -1;
        }
        while (activo == true) {
            int ciclo = 1;
            activo = false;
        }
    }

    public static int suma(int a, int b) {
        int resultado = a + b;
        return resultado;
    }

    public static void saludar(String msg) {
        int contador = 0;
        System.out.println(msg);
    }
}
