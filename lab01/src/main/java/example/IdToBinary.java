package example;

public class IdToBinary {

    public int convertToDecimal(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        if (input.startsWith("BN")) {
            return parseNumber(input, 2, 2, "Binario");
        } else if (input.startsWith("HEX")) {
            return parseNumber(input, 16, 3, "Hexadecimal");
        } else if (input.startsWith("OC")) {
            return parseNumber(input, 8, 2, "Octal");
        }

        throw new IllegalArgumentException("Prefijo no reconocido: " + input);
    }

    private int parseNumber(String input, int radix, int prefixLength, String baseName) {
        //Se elimina el prefijo
        String numberPart = input.substring(prefixLength);
        if (numberPart.isEmpty()) {
            System.out.println("Error: no hay digitps despues del prefijo");
            return 0;
        }
        try {
            return Integer.parseInt(numberPart, radix);
        } catch (NumberFormatException e) {
            System.out.printf("Error: '%s' no es un número %s válido%n",
                    numberPart, baseName);
            return 0;
        }
    }

}