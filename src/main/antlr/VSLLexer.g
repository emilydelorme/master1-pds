lexer grammar VSLLexer;

options {
  language = Java;
}

@header {
  package TP2;
}

WS : (' '|'\n'|'\t') -> skip
   ;

COMMENT : '//' (~'\n')* -> skip
        ;

fragment LETTER : 'a'..'z' ;
fragment DIGIT  : '0'..'9' ;
fragment ASCII  : ~('\n'|'"');

// keywords
LP    : '(' ; // Left parenthesis
RP    : ')' ;
AL	  : '{' ; // accolade gauche
AR	  : '}' ;
ADD   : '+' ;
SUB   : '-' ;
MUL   : '*' ;
DIV   : '/' ;
EQUAL : ':=' ;
INT   : 'INT' ;
VOID  : 'VOID' ;
VIRGULE : ',' ;
IF    : 'IF' ;
THEN  : 'THEN' ;
ELSE  : 'ELSE' ;
FI    : 'FI' ;
WHILE : 'WHILE' ;
DO    : 'DO' ;
DONE  : 'DONE' ;
PROTO : 'PROTO' ;
FUNC  : 'FUNC' ;

// TODO : other keywords

// other tokens (no conflict with keywords in VSL)
IDENT   : LETTER (LETTER|DIGIT)*;
TEXT    : '"' (ASCII)* '"' { setText(getText().substring(1, getText().length() - 1)); };
INTEGER : (DIGIT)+ ;
