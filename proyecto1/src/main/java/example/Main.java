package example;

import antlr.ExprLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;


public class Main {
    private static IdToBinary idToBinary = new IdToBinary();
    public static void main(String[] args) {
        //Entrada
        String input = "BINARIO=BN1101\n" +
                "OCTAL=OC174\n" +
                "HEXADECIMAL=HEX4AF10\n"+"BINARIO + HEXADECIMAL * OCTAL / BINARIO";

        CharStream cs = CharStreams.fromString(input);
        ExprLexer lexer = new ExprLexer(cs);

        Token token;
        while ((token = lexer.nextToken()).getType() != Token.EOF) {
            String typeName = ExprLexer.VOCABULARY.getSymbolicName(token.getType());
            System.out.printf("Token %-12s  texto: '%s'%n", typeName, token.getText());
            if ("BIN".equals(typeName)) {
                System.out.println(idToBinary.convertToDecimal(token.getText()));
            }
            else if ("HEX".equals(typeName)) {
                System.out.println(idToBinary.convertToDecimal(token.getText()));
            }
            else if ("OC".equals(typeName)) {
                System.out.println(idToBinary.convertToDecimal(token.getText()));
            }
        }
    }
}