parser grammar VSLParser;

options {
  language = Java;
  tokenVocab = VSLLexer;
}

@header {
  package TP2;

  import java.util.stream.Collectors;
  import java.util.Arrays;
}

// TODO : other rules

// CHANGER EXPRESSION DANS LE CODE PAR UNIT QU'IL FAUT QU'ON CREE
// Interface unit à créer et prototype & function implements unit
// program(List<Unit>)
program returns [TP2.ASD.Program out]
    : (u=unit)? EOF { $out = new TP2.ASD.Program($u.out); }
    ;

// interface 
unit returns [List<TP2.ASD.Unit> out]
@init { List<TP2.ASD.Unit> units = new ArrayList<>(); }
	: (p=prototype { units.add($p.out); } )* { $out = units; }
	| (f=function { units.add($f.out); } )* { $out = units; }
	;

// implements Unit
// prototype(type, IDENT, List<parametre>)
prototype returns [TP2.ASD.Prototype out]
	: PROTO t=type IDENT LP p=parameters RP { $out = new TP2.ASD.Prototype($t.out, IDENT.text, $p.out) }
	;

// Gérer les fonction sans accolades "(block | statement)"
// implements Unit
// function(type, IDENT, List<parametre>, block)
function
	: PROTO t=type IDENT LP p=parameters RP b=block { $out = new TP2.ASD.Function($t.out, IDENT.text, $p.out, $b.out) }
	;

parameters
	: IDENT?
	| IDENT (VIRGULE IDENT)+
	;

block
	: AL (statement)* AR
	;

statement returns [ASD.Statement out]
	: declaration
    | affectation
    | expression
    
	;

declaration returns [TP2.ASD.Statement.Declaration out]
    : type (IDENT) { $out = new TP2.ASD.Statement.Declaration($IDENT.text);} (VIRGULE IDENT { $out = new TP2.ASD.Declaration($IDENT.text);} )*
    ;

affectation returns [TP2.ASD.Statement.Affectation out]
    : IDENT EQUAL l=expression  { $out = new TP2.ASD.Statement.Affectation($IDENT.text, $l.out); }
    ;


expression returns [TP2.ASD.ExpressionInterface out]
    : l=expressionPrioritaire ADD r=expressionPrioritaire  { $out = new TP2.ASD.Expression.AddExpression($l.out, $r.out); }
    | l=expressionPrioritaire SUB r=expressionPrioritaire  { $out = new TP2.ASD.Expression.SubExpression($l.out, $r.out); }
    ;

expressionPrioritaire returns [TP2.ASD.ExpressionInterface out]
    : l=factor MUL r=factor  { $out = new TP2.ASD.Expression.MulExpression($l.out, $r.out); }
    | l=factor DIV r=factor  { $out = new TP2.ASD.Expression.DivExpression($l.out, $r.out); }
    | f=factor { $out = $f.out; }
    ;

factor returns [TP2.ASD.ExpressionInterface out]
    : p=primary { $out = $p.out; }
    | LP e=expression RP { $out = $e.out; }
    ;

type
	: INT
	| VOID
	;

primary returns [TP2.ASD.ExpressionInterface out]
    : INTEGER { $out = new TP2.ASD.IntegerExpression($INTEGER.int); }
    ;
