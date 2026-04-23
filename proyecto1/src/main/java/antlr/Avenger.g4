grammar Avenger;

// =====================
// REGLAS DEL PARSER
// =====================


prog
    : statement* EOF
    ;
//El numero de producción se verá reflejado en el arbol con el comando
// antlr4-parse.exe Avenger.g4 prog -gui -input 'Nombre del test'.txt
statement
    : tipoVar IDENTIFICADOR JARVIS expr SEMI                        # StmtVarDecl      //Produccion 1
    | IDENTIFICADOR JARVIS expr SEMI                                # StmtAssign       //Produccion 2
    | VISION LPAREN condition RPAREN LBRACE statement* RBRACE
      (WANDA LBRACE statement* RBRACE)?                             # StmtIf           //Produccion 3
    | LOKI LPAREN condition RPAREN LBRACE statement* RBRACE         # StmtWhile        //Produccion 4
    | FURY LPAREN tipoVar IDENTIFICADOR JARVIS expr SEMI
             condition SEMI
             IDENTIFICADOR JARVIS expr
      RPAREN LBRACE statement* RBRACE                               # StmtFor          //Produccion 5
    | tipoVar IDENTIFICADOR LPAREN (param (COMMA param)*)? RPAREN
      LBRACE statement* RBRACE                                      # StmtFuncDecl     //Produccion 6
    | BOB IDENTIFICADOR LPAREN (param (COMMA param)*)? RPAREN
      LBRACE statement* RBRACE                                      # StmtFuncDeclVoid //Produccion 7
    | RETURN expr SEMI                                              # StmtReturn       //Produccion 8
    | GAMORA LPAREN IDENTIFICADOR RPAREN SEMI                       # StmtRead         //Produccion 9
    | NEBULA LPAREN expr RPAREN SEMI                                # StmtWrite        //Produccion 10
    | RECRUIT STRING_ROGERS SEMI                                    # StmtImport       //Produccion 11
    | IDENTIFICADOR LPAREN (expr (COMMA expr)*)? RPAREN SEMI        # StmtFuncCall     //Produccion 12
    ;

// --- Tipos ---

tipoVar
    : STARK | BANNER | ROGERS | THOR    # TipoVar  //Produccion 1
    ;

param
    : tipoVar IDENTIFICADOR  //Produccion 1
    ;

// --- Condición ---

condition
    : expr (PARKER | ODIN | NOJARVIS | EQEQ) expr  //Produccion 1
    ;

// --- Expresiones ---

expr
    : expr (MULT | DIV) expr      # ExprMulDiv    //Produccion 1
    | expr (PLUS | MINUS) expr    # ExprSumResta  //Produccion 2
    | primary                     # ExprPrimary   //Produccion 3
    ;

// --- Primary ---

primary
    : MINUS primary                                             # PrimaryNegativo  //Produccion 1
    | LPAREN expr RPAREN                                        # PrimaryAgrupado  //Produccion 2
    | IDENTIFICADOR LPAREN (expr (COMMA expr)*)? RPAREN         # PrimaryFuncCall  //Produccion 3
    | IDENTIFICADOR                                             # PrimaryId        //Produccion 4
    | NUMERO_STARK                                              # PrimaryEntero    //Produccion 5
    | NUMERO_BANNER                                             # PrimaryFlotante  //Produccion 6
    | STRING_ROGERS                                             # PrimaryCadena    //Produccion 7
    | BOOL_THOR                                                 # PrimaryBooleano  //Produccion 8
    ;

// =====================
// REGLAS DEL LÉXICO
// =====================

// ----- Tipos -----
STARK   : 'stark'   ;
BANNER  : 'banner'  ;
ROGERS  : 'rogers'  ;
THOR    : 'thor'    ;
BOB     : 'bob'     ;

// ----- Control de flujo -----
VISION  : 'vision'  ;
WANDA   : 'wanda'   ;
LOKI    : 'loki'    ;
FURY    : 'fury'    ;

// ----- I/O e importación -----
GAMORA  : 'gamora'  ;
NEBULA  : 'nebula'  ;
RECRUIT : 'recruit' ;

// ----- Otras palabras clave -----
RETURN  : 'return'  ;

// ----- Operadores -----
JARVIS          : 'jarvis'  ; // ==
PARKER          : '<'       ;
ODIN            : '>'       ;
NOJARVIS        : '!='      ;
JARVISJARVIS    : '=='      ;

PLUS    : '+'       ;
MINUS   : '-'       ;
MULT    : '*'       ;
DIV     : '/'       ;

// ----- Símbolos -----
LPAREN  : '('       ;
RPAREN  : ')'       ;
LBRACE  : '{'       ;
RBRACE  : '}'       ;
SEMI    : ';'       ;
COMMA   : ','       ;

// ----- Literales -----
BOOL_THOR     : 'TRUE' | 'FALSE'        ;
NUMERO_BANNER : [0-9]+ '.' [0-9]+       ;
NUMERO_STARK  : [0-9]+                  ;
STRING_ROGERS : '"' (~["\r\n])* '"'     ;

// ----- Identificadores -----
IDENTIFICADOR : [a-zA-Z_][a-zA-Z0-9_]* ;

// ----- Ignorados -----
WS      : [ \t\r\n]+   -> skip ;
COMMENT : '//' ~[\r\n]* -> skip ;