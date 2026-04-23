package example;

import antlr.AvengerBaseVisitor;

//Recorre el árbol de análisis sintáctico y:
//1. Construye una cadena de código Java (traducción).
//2. Registra cada variable declarada en la tabla de símbolos (List<Variable>).

public class EvalVisitor extends AvengerBaseVisitor<String> {


}