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
