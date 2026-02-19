package example;

import java.util.*;

public class ShuntingYard {

    // Clase para representar tokens procesados
    public static class Token {
        String value;
        TokenType type;

        public enum TokenType {
            NUMBER,      // Número decimal ya convertido
            VARIABLE,    // Identificador de variable
            OPERATOR,    // +, -, *, /
            LPAREN,      // (
            RPAREN       // )
        }

        public Token(String value, TokenType type) {
            this.value = value;
            this.type = type;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    //Convierte expresión infix a postfix usando Shunting Yard
    public static List<Token> infixToPostfix(List<Token> tokens) {
        List<Token> output = new ArrayList<>();
        Stack<Token> operators = new Stack<>();

        for (Token token : tokens) {
            switch (token.type) {
                case NUMBER:
                case VARIABLE:
                    // Números y variables van directo al output
                    output.add(token);
                    break;

                case OPERATOR:
                    // Mientras haya operadores con mayor o igual precedencia en el stack
                    while (!operators.isEmpty() &&
                            operators.peek().type == Token.TokenType.OPERATOR &&
                            precedence(operators.peek().value) >= precedence(token.value)) {
                        output.add(operators.pop());
                    }
                    operators.push(token);
                    break;

                case LPAREN:
                    operators.push(token);
                    break;

                case RPAREN:
                    // Sacar operadores hasta encontrar el paréntesis izquierdo
                    while (!operators.isEmpty() &&
                            operators.peek().type != Token.TokenType.LPAREN) {
                        output.add(operators.pop());
                    }
                    if (!operators.isEmpty()) {
                        operators.pop(); // Remover '('
                    }
                    break;
            }
        }

        // Vaciar operadores restantes
        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    //Evalua una expresion en notacion postfix
    public static int evaluatePostfix(List<Token> postfix, Map<String, Integer> symbolTable)
            throws Exception {
        Stack<Integer> stack = new Stack<>();

        for (Token token : postfix) {
            switch (token.type) {
                case NUMBER:
                    stack.push(Integer.parseInt(token.value));
                    break;

                case VARIABLE:
                    if (!symbolTable.containsKey(token.value)) {
                        throw new Exception("Error: Variable '" + token.value + "' no declarada");
                    }
                    stack.push(symbolTable.get(token.value));
                    break;

                case OPERATOR:
                    if (stack.size() < 2) {
                        throw new Exception("Error: Expresión sintácticamente incorrecta");
                    }

                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    int result = applyOperator(token.value, operand1, operand2);
                    stack.push(result);
                    break;

                default:
                    throw new Exception("Error: Token inesperado en evaluación");
            }
        }

        if (stack.size() != 1) {
            throw new Exception("Error: Expresión sintácticamente incorrecta");
        }

        return stack.pop();
    }

    //Aplica un operador a dos operandos
    private static int applyOperator(String operator, int a, int b) throws Exception {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    throw new Exception("Error: División por cero");
                }
                yield a / b;
            }
            default -> throw new Exception("Error: Operador desconocido '" + operator + "'");
        };
    }

    //Retorna la precedencia de un operador
    private static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> 0;
        };
    }

    //Convierte lista de tokens a string para debug
    public static String tokensToString(List<Token> tokens) {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append(token.value).append(" ");
        }
        return sb.toString().trim();
    }
}