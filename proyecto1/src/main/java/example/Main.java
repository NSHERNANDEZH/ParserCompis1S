package example;

import antlr.ExprLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Entrada
        String input = "BINARIO=BN01\n" +
                "OCTAL=OC174\n" +
                "HEXADECIMAL=HEX4AF10\n"+"BINARIO + HEXADECIMAL * OCTAL / BINARIO";

        CharStream cs = CharStreams.fromString(input);
        ExprLexer lexer = new ExprLexer(cs);

        Token token;
        while ((token = lexer.nextToken()).getType() != Token.EOF) {
            String typeName = ExprLexer.VOCABULARY.getSymbolicName(token.getType());
            System.out.printf("Token %-12s  texto: '%s'%n", typeName, token.getText());
        }
    }
}