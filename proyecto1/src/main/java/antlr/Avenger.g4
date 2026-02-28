grammar Avenger;

// =====================
// REGLAS DEL PARSER
// =====================

prog
    : statement* EOF
    ;

statement
    : varDecl SEMI
    | assignStmt SEMI
    | ifStmt
    | whileStmt
    | forStmt
    | funcDecl
    | returnStmt SEMI
    | readStmt SEMI
    | writeStmt SEMI
    | importStmt SEMI
    | funcCall SEMI
    ;

// Declaración de variable: stark nombre jarvis 5;
varDecl
    : tipo IDENTIFICADOR JARVIS expr
    ;

// Asignación: x jarvis 5;
assignStmt
    : IDENTIFICADOR JARVIS expr
    ;

// IF / ELSE
ifStmt
    : VISION LPAREN condition RPAREN block (WANDA block)?
    ;

// WHILE
whileStmt
    : LOKI LPAREN condition RPAREN block
    ;

// FOR
forStmt
    : FURY LPAREN varDecl SEMI condition SEMI assignStmt RPAREN block
    ;

// Funciones
funcDecl
    : tipo IDENTIFICADOR LPAREN paramList? RPAREN block
    ;

paramList
    : param (COMMA param)*
    ;

param
    : tipo IDENTIFICADOR
    ;

returnStmt
    : RETURN expr
    ;

// Llamada a función
funcCall
    : IDENTIFICADOR LPAREN argList? RPAREN
    ;

argList
    : expr (COMMA expr)*
    ;

// Read y Write
readStmt
    : GAMORA LPAREN IDENTIFICADOR RPAREN
    ;

writeStmt
    : NEBULA LPAREN expr RPAREN
    ;

// Import
importStmt
    : RECRUIT STRING_ROGERS
    ;

// Bloque de código
block
    : LBRACE statement* RBRACE
    ;

// Tipos primitivos
tipo
    : STARK      // int
    | BANNER     // float
    | ROGERS     // string
    | THOR       // boolean
    | BOB        // void
    ;

// Condiciones
condition
    : expr (PARKER | ODIN | JARVIS | NOJARVIS) expr
    ;

// -------------------------------------------------------
// Expresiones con precedencia correcta
// SIN recursión a la izquierda: se usa el patrón iterativo
//   expr  → term  ( ('+' | '-') term  )*
//   term  → factor( ('*' | '/') factor)*
//   factor→ '-' factor | atom
// -------------------------------------------------------
expr
    : term ((PLUS | MINUS) term)*
    ;

term
    : factor ((MULT | DIV) factor)*
    ;

factor
    : MINUS factor
    | atom
    ;

atom
    : LPAREN expr RPAREN
    | funcCall
    | IDENTIFICADOR
    | NUMERO_STARK
    | NUMERO_BANNER
    | STRING_ROGERS
    | BOOL_THOR
    ;

// =====================
// REGLAS DEL LÉXICO
// =====================

// ----- Tipos (palabras clave) -----
STARK   : 'stark'   ;   // int
BANNER  : 'banner'  ;   // float
ROGERS  : 'rogers'  ;   // string
THOR    : 'thor'    ;   // boolean
BOB     : 'bob'     ;   // void

// ----- Estructuras de control -----
VISION  : 'vision'  ;   // if
WANDA   : 'wanda'   ;   // else
LOKI    : 'loki'    ;   // while
FURY    : 'fury'    ;   // for

// ----- Entrada / Salida / Importación -----
GAMORA  : 'gamora'  ;   // read
NEBULA  : 'nebula'  ;   // write
RECRUIT : 'recruit' ;   // import

// ----- Niveles de acceso -----
CITIZEN : 'citizen' ;   // public
SHIELD  : 'shield'  ;   // protected
HYDRA   : 'hydra'   ;   // private
HAPPY   : 'happy'   ;   // default

// ----- Otras palabras clave -----
RETURN  : 'return'  ;

// ----- Operadores de comparación / asignación -----
JARVIS  : 'jarvis'  ;   // =
PARKER  : 'parker'  ;   // <
ODIN    : 'odin'    ;   // >
NOJARVIS: 'nojarvis';   // !=

// ----- Operadores aritméticos -----
PLUS    : '+'  ;
MINUS   : '-'  ;
MULT    : '*'  ;
DIV     : '/'  ;

// ----- Símbolos especiales (tokens nombrados) -----
// Definidos para que no los genere como tokens no identificados
LPAREN  : '('  ;   // paréntesis abre
RPAREN  : ')'  ;   // paréntesis cierra
LBRACE  : '{'  ;   // llave abre
RBRACE  : '}'  ;   // llave cierra
SEMI    : ';'  ;   // punto y coma
COMMA   : ','  ;   // coma

// ----- Literales -----

BOOL_THOR     : 'TRUE' | 'FALSE'        ;   // booleano
NUMERO_BANNER : [0-9]+ '.' [0-9]+       ;   // flotante
NUMERO_STARK  : [0-9]+                  ;   // entero
STRING_ROGERS : '"' (~["\r\n])* '"'     ;   // cadena

// ----- Identificadores -----
IDENTIFICADOR : [a-zA-Z_][a-zA-Z0-9_]* ;

// ----- Ignorar espacios y comentarios -----
WS      : [ \t\r\n]+    -> skip ;
COMMENT : '//' ~[\r\n]*  -> skip ;