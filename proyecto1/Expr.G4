grammar Expr;
prog:   (expr NEWLINE)* ;
expr:   var ('*'|'/') expr
    |   var ('+'|'-') var
    |   var	
    ;
NEWLINE : [\r\n]+ ;
BIN     : BN.(0|1)+
OC      : OC.(0|1|2|3|4|5|6|7)+
HEX     : HEX.(0|1|2|3|4|5|6|7|8|9|A|B|C|E|F)+
var     : IDENTIFICADOR ´=´(BIN|OC|HEX)




