Laboratorio 1 

Integrantes del Equipo

Integrante 1: Nery Hernández - 1098824
Integrante 2: Gabriel Toyom - 1051524
Integrante 3: Marc Schaub - 1243424

Descripción del Proyecto
Este laboratorio implementa un analizador léxico y sintáctico para un lenguaje de programación experimental desarrollado por la empresa "Macrosystems". El lenguaje permite declarar variables numéricas en diferentes bases (binaria, octal y hexadecimal) y realizar operaciones aritméticas con ellas.
Características Principales

Declaración de variables en múltiples bases:

Variables binarias: Solo pueden usar dígitos 0 y 1
Variables octales: Solo pueden usar dígitos del 0 al 7
Variables hexadecimales: Pueden usar valores del 0 a F


Análisis léxico: Tokenización de la entrada utilizando expresiones regulares definidas en ANTLR
Análisis sintáctico: Implementacion de algoritmo shunting yard para la corrección sintáctica de expresiones aritméticas
Conversión y evaluación:

Convierte números de diferentes bases a decimal
Transforma expresiones infijas a notación postfija
Evalúa el resultado utilizando el algoritmo Shunting Yard


Gramatica utilizada: 

grammar Expr;

prog: (expr| var NEWLINE*)* ;

var
    : IDENTIFICADOR EQUAL (BIN | OC | HEX)
    ;

expr
    : expr ('*'|'/') expr   # MulDiv
    | expr ('+'|'-') expr   # AddSub
    | IDENTIFICADOR         # Id
    | '(' expr ')'          # Parens
    ;

BIN : 'BN' [0-1]+ ;
OC  : 'OC' [0-7]+ ;
HEX : 'HEX' [0-9A-F]+ ;

EQUAL: '=';

IDENTIFICADOR : [a-zA-Z]+ ;

NEWLINE : [\r\n]+ ;
WS : [ \t]+ -> skip ;

INSTRUCCIONES DE USO

1. Preparar el Archivo de Entrada
Crear o modificar el archivo entrada.txt en la raíz del proyecto con el código que desea analizar. El archivo debe seguir la sintaxis del lenguaje:
Sintaxis para declaración de variables:
variable = BNvalor      # Para binarios
variable = OCvalor      # Para octales
variable = HEXvalor     # Para hexadecimales

Sintaxis para expresiones aritméticas:
variable1 + variable2 * variable3
(variable1 + variable2) / variable3

2. Ejemplo de Archivo de Entrada
a = BN1010
b = OC17
c = HEXFF

a + b * c


3. Ejecutar el Programa
Ejecutar la clase Main.java:
bashjava example.Main
4. Interpretar la Salida


El programa mostrará:

Tokenización: Lista de todos los tokens identificados

Token BIN          texto: 'BN1010'
Decimal: 10
Token IDENTIFICADOR texto: 'a'
...

Tabla de Símbolos: Variables declaradas con sus valores en decimal

a               = 10
b               = 15
c               = 255

Análisis con Shunting Yard:

Expresión en notación infija
Validación de sintaxis (paréntesis balanceados)
Conversión a notación postfija
Evaluación del resultado final en decimal



Expresion infix:   a + b * c
Tokens:            [a, +, b, *, c]
Expresion postfix: [a, b, c, *, +]

RESULTADO FINAL: 3835
Funcionamiento del Programa
Fase 1: Análisis Léxico
El lexer generado por ANTLR escanea el archivo de entrada y genera tokens según las expresiones regulares definidas en la gramática.
Fase 2: Conversión de Bases
La clase IdToBinary convierte los valores en diferentes bases a su equivalente decimal:

Binario (BN): Base 2
Octal (OC): Base 8
Hexadecimal (HEX): Base 16

Fase 3: Tabla de Símbolos
Se construye una tabla hash que mapea los identificadores de variables a sus valores en decimal.
Fase 4: Análisis Sintáctico
Se valida que:

Las variables estén declaradas antes de usarse
Los paréntesis estén balanceados
La expresión sea sintácticamente correcta

Fase 5: Algoritmo Shunting Yard

Convierte la expresión infija a notación postfija
Evalúa la expresión postfija usando una pila
Sustituye las variables por sus valores decimales
Retorna el resultado final en decimal
