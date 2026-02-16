package example;

import java.util.*;


public class ExpressionParser {

    // Parsea string de expresión a tokens
    public static List<ShuntingYard.Token> parseExpression(String expression) {
        List<ShuntingYard.Token> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Ignorar espacios
            if (Character.isWhitespace(c)) {
                if (!currentToken.isEmpty()) {
                    addToken(tokens, currentToken.toString());
                    currentToken = new StringBuilder();
                }
                continue;
            }

            // Operadores y parentesis
            if (isOperatorOrParen(c)) {
                if (!currentToken.isEmpty()) {
                    addToken(tokens, currentToken.toString());
                    currentToken = new StringBuilder();
                }
                addOperatorToken(tokens, c);
            } else {
                currentToken.append(c);
            }
        }

        if (!currentToken.isEmpty()) {
            addToken(tokens, currentToken.toString());
        }

        return tokens;
    }

    private static boolean isOperatorOrParen(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    private static void addToken(List<ShuntingYard.Token> tokens, String value) {
        if (value.matches("\\d+")) {
            tokens.add(new ShuntingYard.Token(value, ShuntingYard.Token.TokenType.NUMBER));
        } else {
            tokens.add(new ShuntingYard.Token(value, ShuntingYard.Token.TokenType.VARIABLE));
        }
    }

    private static void addOperatorToken(List<ShuntingYard.Token> tokens, char c) {
        String value = String.valueOf(c);

        if (c == '(') {
            tokens.add(new ShuntingYard.Token(value, ShuntingYard.Token.TokenType.LPAREN));
        } else if (c == ')') {
            tokens.add(new ShuntingYard.Token(value, ShuntingYard.Token.TokenType.RPAREN));
        } else {
            tokens.add(new ShuntingYard.Token(value, ShuntingYard.Token.TokenType.OPERATOR));
        }
    }

    // Validar parentesis balanceados
    public static boolean isBalanced(String expression) {
        int count = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') count++;
            if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
}