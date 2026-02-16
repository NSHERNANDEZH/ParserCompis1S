package example;

import antlr.ExprLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.util.*;

public class Main {
    private static IdToBinary idToBinary = new IdToBinary();

    public static void main(String[] args) {
        // Entrada
        String input = "X=BN1000\n" +
                "Y=BN0010\n" +
                "Z=OC0011\n" +
                "X + Y * Z";
        String fileName = "entrada.txt"; // agregado para el txt
        System.out.println("COMPILADOR - LABORATORIO 1");
        System.out.println("==================================================\n");

        // Tokenizacion
        System.out.println("=== TOKENIZACION ===\n");

// 3. AGREGADO: Bloque try para manejar errores de lectura de archivo**
        try {
            // 4. CAMBIO: CharStreams.fromString -> CharStreams.fromFileName**
            CharStream cs = CharStreams.fromFileName(fileName); // fin agregado
        ExprLexer lexer = new ExprLexer(cs);

        // Recolectar todos los tokens
        List<Token> allTokens = new ArrayList<>();
        Token token;
        while ((token = lexer.nextToken()).getType() != Token.EOF) {
            allTokens.add(token);

            String typeName = ExprLexer.VOCABULARY.getSymbolicName(token.getType());
            System.out.printf("Token %-12s  texto: '%s'%n", typeName, token.getText());

            // Conversion a decimal
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

        // evaluar con shunting yard
        System.out.println("\n==================================================");
        analyzeAndEvaluate(allTokens);

        } catch (IOException e) { // 5. MANEJO DE ERROR DE ARCHIVO Agregado
            System.err.println("Error: No se encontró el archivo '" + fileName + "'.");
            System.err.println("Asegúrate de que esté en la carpeta raíz del proyecto.");
        } // fin agregado
    }


    private static void analyzeAndEvaluate(List<Token> allTokens) {
        Map<String, Integer> symbolTable = new HashMap<>();
        StringBuilder expressionBuilder = new StringBuilder();
        boolean foundExpression = false;


        // Procesar tokens para extraer declaraciones y expresiones
        for (int i = 0; i < allTokens.size(); i++) {
            Token currentToken = allTokens.get(i);
            String typeName = ExprLexer.VOCABULARY.getSymbolicName(currentToken.getType());
            String text = currentToken.getText();

            if ("IDENTIFICADOR".equals(typeName)) {
                // Verificar si es asignacion
                if (i + 2 < allTokens.size()) {
                    Token nextToken = allTokens.get(i + 1);
                    String nextType = ExprLexer.VOCABULARY.getSymbolicName(nextToken.getType());

                    if ("EQUAL".equals(nextType)) {
                        // Es asignacion
                        Token valueToken = allTokens.get(i + 2);
                        String valueType = ExprLexer.VOCABULARY.getSymbolicName(valueToken.getType());
                        String valueText = valueToken.getText();

                        if ("BIN".equals(valueType) || "HEX".equals(valueType) || "OC".equals(valueType)) {
                            int decimalValue = idToBinary.convertToDecimal(valueText);
                            symbolTable.put(text, decimalValue);
                            System.out.println("Variable declarada: " + text + " = " + decimalValue + " (decimal)");
                        }

                        i += 2; // Saltar EQUAL y valor
                        continue;
                    }
                }

                // No es asignacion, es parte de expresion
                foundExpression = true;
                if (expressionBuilder.length() > 0) {
                    expressionBuilder.append(" ");
                }
                expressionBuilder.append(text);

            } else if (foundExpression && !"NEWLINE".equals(typeName)) {
                // Agregar operadores a la expresion
                if (expressionBuilder.length() > 0) {
                    expressionBuilder.append(" ");
                }
                expressionBuilder.append(text);
            }
        }

        // Mostrar tabla de simbolos
        System.out.println("\n=== TABLA DE SIMBOLOS ===\n");
        for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
            System.out.printf("%-15s = %d%n", entry.getKey(), entry.getValue());
        }

        // Procesar expresion con Shunting Yard
        String expression = expressionBuilder.toString().trim();
        if (!expression.isEmpty()) {
            processExpressionWithShuntingYard(expression, symbolTable);
        }
    }


    private static void processExpressionWithShuntingYard(String expression, Map<String, Integer> symbolTable) {
        System.out.println("\n==================================================");
        System.out.println("\n=== SHUNTING YARD ===\n");
        System.out.println("Expresion infix:   " + expression);

        try {
            // 1. Validar parentesis balanceados
            if (!ExpressionParser.isBalanced(expression)) {
                throw new Exception("Parentesis no balanceados");
            }
            System.out.println("Sintaxis valida (parentesis balanceados)");

            // 2. Parsear expresion a tokens
            List<ShuntingYard.Token> infixTokens = ExpressionParser.parseExpression(expression);
            System.out.println("\nTokens:            " + ShuntingYard.tokensToString(infixTokens));

            // 3. Convertir a postfix usando Shunting Yard
            List<ShuntingYard.Token> postfixTokens = ShuntingYard.infixToPostfix(infixTokens);
            System.out.println("Expresion postfix: " + ShuntingYard.tokensToString(postfixTokens));

            // 4. Evaluar expresion postfix
            int result = ShuntingYard.evaluatePostfix(postfixTokens, symbolTable);

            System.out.println("\n==================================================");
            System.out.println("\nRESULTADO FINAL: " + result);
            System.out.println("\n==================================================");

        } catch (Exception e) {
            System.err.println("\nERROR: " + e.getMessage());
        }
    }
}