grammar Avenger;

// =====================
// REGLAS DEL PARSER
// =====================

prog
    : statement* EOF
    ;

statement
    : varDecl ';'
    | assignStmt ';'
    | ifStmt
    | whileStmt
    | forStmt
    | funcDecl
    | returnStmt ';'
    | readStmt ';'
    | writeStmt ';'
    | importStmt ';'
    | funcCall ';'
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
    : VISION '(' condition ')' block (WANDA block)?
    ;

// WHILE
whileStmt
    : LOKI '(' condition ')' block
    ;

// FOR
forStmt
    : FURY '(' varDecl ';' condition ';' assignStmt ')' block
    ;

// Funciones
funcDecl
    : tipo IDENTIFICADOR '(' paramList? ')' block
    ;

paramList
    : param (',' param)*
    ;

param
    : tipo IDENTIFICADOR
    ;

returnStmt
    : 'return' expr
    ;

// Llamada a función
funcCall
    : IDENTIFICADOR '(' argList? ')'
    ;

argList
    : expr (',' expr)*
    ;

// Read y Write
readStmt
    : GAMORA '(' IDENTIFICADOR ')'
    ;

writeStmt
    : NEBULA '(' expr ')'
    ;

// Import
importStmt
    : RECRUIT STRING_ROGERS
    ;

// Bloque de código
block
    : '{' statement* '}'
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

// Expresiones con precedencia correcta (sin recursión izquierda)
expr
    : expr ('*' | '/') expr    // multiplicación y división
    | expr ('+' | '-') expr    // suma y resta
    | '(' expr ')'             // agrupación
    | funcCall                 // llamada a función
    | IDENTIFICADOR            // variable
    | NUMERO_STARK             // entero
    | NUMERO_BANNER            // flotante
    | STRING_ROGERS            // cadena
    | BOOL_THOR                // booleano
    ;

// =====================
// REGLAS DEL LÉXICO
// =====================

// Tipos (palabras clave)
STARK   : 'stark'   ;   // int
BANNER  : 'banner'  ;   // float
ROGERS  : 'rogers'  ;   // string
THOR    : 'thor'    ;   // boolean
BOB     : 'bob'     ;   // void

// Estructuras de control
VISION  : 'vision'  ;   // if
WANDA   : 'wanda'   ;   // else
LOKI    : 'loki'    ;   // while
FURY    : 'fury'    ;   // for

// Entrada / Salida / Importación
GAMORA  : 'gamora'  ;   // read
NEBULA  : 'nebula'  ;   // write
RECRUIT : 'recruit' ;   // import

// Niveles de acceso
CITIZEN : 'citizen' ;   // public
SHIELD  : 'shield'  ;   // protected
HYDRA   : 'hydra'   ;   // private
HAPPY   : 'happy'   ;   // default

// Operadores y desigualdades
JARVIS  : 'jarvis'  ;   // =
PARKER  : 'parker'  ;   // <
ODIN    : 'odin'    ;   // >
NOJARVIS: 'nojarvis';   // !=

// Literales (NUMERO_BANNER antes que NUMERO_STARK para evitar conflictos)
BOOL_THOR     : 'TRUE' | 'FALSE'        ;   // booleano
NUMERO_BANNER : [0-9]+ '.' [0-9]+       ;   // flotante
NUMERO_STARK  : [0-9]+                  ;   // entero
STRING_ROGERS : '"' (~["\r\n])* '"'     ;   // cadena

// Identificadores (deben ir DESPUÉS de todas las palabras clave)
IDENTIFICADOR : [a-zA-Z_][a-zA-Z0-9_]* ;

// Ignorar espacios y comentarios
WS      : [ \t\r\n]+    -> skip ;
COMMENT : '//' ~[\r\n]*  -> skip ;