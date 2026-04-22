grammar Avenger;

// =====================
// REGLAS DEL PARSER
// =====================

prog
    : statement* EOF
    ;

statement
    : tipoVar IDENTIFICADOR JARVIS expr SEMI                     # StmtVarDecl
    | IDENTIFICADOR JARVIS expr SEMI                             # StmtAssign
    | VISION LPAREN condition RPAREN LBRACE statement* RBRACE
      (WANDA LBRACE statement* RBRACE)?                          # StmtIf
    | LOKI LPAREN condition RPAREN LBRACE statement* RBRACE      # StmtWhile
    | FURY LPAREN tipoVar IDENTIFICADOR JARVIS expr SEMI
             condition SEMI
             IDENTIFICADOR JARVIS expr
      RPAREN LBRACE statement* RBRACE                            # StmtFor
    | tipoVar IDENTIFICADOR LPAREN (param (COMMA param)*)? RPAREN
      LBRACE statement* RBRACE                                   # StmtFuncDecl
    | BOB IDENTIFICADOR LPAREN (param (COMMA param)*)? RPAREN
      LBRACE statement* RBRACE                                   # StmtFuncDeclVoid
    | RETURN expr SEMI                                           # StmtReturn
    | GAMORA LPAREN IDENTIFICADOR RPAREN SEMI                    # StmtRead
    | NEBULA LPAREN expr RPAREN SEMI                             # StmtWrite
    | RECRUIT STRING_ROGERS SEMI                                 # StmtImport
    | IDENTIFICADOR LPAREN (expr (COMMA expr)*)? RPAREN SEMI     # StmtFuncCall
    ;

// --- Tipos ---

tipoVar
    : STARK     # TipoInt
    | BANNER    # TipoFloat
    | ROGERS    # TipoString
    | THOR      # TipoBool
    ;

param
    : tipoVar IDENTIFICADOR
    ;

// --- Condición ---

condition
    : expr opRel expr
    ;

opRel
    : PARKER    # OpMenor
    | ODIN      # OpMayor
    | NOJARVIS  # OpDistinto
    | EQEQ      # OpIgual
    ;

// --- Expresiones ---

expr
    : expr PLUS term    # ExprSuma
    | expr MINUS term   # ExprResta
    | term              # ExprTerm
    ;

term
    : term MULT primary    # TermMult
    | term DIV primary     # TermDiv
    | primary              # TermPrimary
    ;

primary
    : MINUS primary                                          # PrimaryNegativo
    | LPAREN expr RPAREN                                     # PrimaryAgrupado
    | IDENTIFICADOR LPAREN (expr (COMMA expr)*)? RPAREN      # PrimaryFuncCall
    | IDENTIFICADOR                                          # PrimaryId
    | NUMERO_STARK                                           # PrimaryEntero
    | NUMERO_BANNER                                          # PrimaryFlotante
    | STRING_ROGERS                                          # PrimaryCadena
    | BOOL_THOR                                              # PrimaryBooleano
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
JARVIS  : 'jarvis'  ;
PARKER  : '<'       ;
ODIN    : '>'       ;
NOJARVIS: '!='      ;
EQEQ    : '=='      ;

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