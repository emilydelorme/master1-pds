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

// program(List<UnitInterface>)
program returns [TP2.ASD.Program out]
@init { List<TP2.ASD.UnitInterface> units = new ArrayList<>(); }
    : (u=unit { units.add($u.out); } )* EOF { $out = new TP2.ASD.Program(units); }
    ;

// interface 
unit returns [TP2.ASD.UnitInterface out]
	: p=prototype { $out = $p.out; }
	| f=function { $out = $f.out; }
	;

// implements Unit
// prototype(type, IDENT, List<parametre>)
prototype returns [TP2.ASD.Unit.Prototype out]
	: PROTO t=type IDENT LP p=parameters RP { $out = new TP2.ASD.Unit.Prototype($t.out, $IDENT.text, $p.out); }
	;

// implements Unit
// function(type, IDENT, List<parametre>, statement)
function returns [TP2.ASD.Unit.Function out]
	: FUNC t=type IDENT LP p=parameters RP s=statement { $out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $s.out); }
	| FUNC t=type IDENT LP p=parameters RP b=block { $out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $b.out); }
	;

parameters returns [List<String> out]
@init { List<String> parametres = new ArrayList<>(); }
	: (IDENT { parametres.add($IDENT.text); } )? { $out = parametres; }
	| (IDENT { parametres.add($IDENT.text); } ) (VIRGULE IDENT { parametres.add($IDENT.text); })+ { $out = parametres; }
	;

statement returns [TP2.ASD.StatementInterface out]
	: d=declaration { $out = $d.out; }
    | a=affectation { $out = $a.out; }
    | ifS=ifState { $out = $ifS.out; }
    | ifES=ifElseState { $out = $ifES.out; }
    | w=whileState { $out = $w.out; }
    | b=block { $out = $b.out; }
    | r=returnState { $out = $r.out; }
	;

declaration returns [TP2.ASD.Statement.Declaration out]
    : type (IDENT) { $out = new TP2.ASD.Statement.Declaration($IDENT.text);} (VIRGULE IDENT { $out = new TP2.ASD.Statement.Declaration($IDENT.text);} )*
    ;

affectation returns [TP2.ASD.Statement.Affectation out]
    : IDENT EQUAL l=expression  { $out = new TP2.ASD.Statement.Affectation($IDENT.text, $l.out); }
    ;

ifState returns [TP2.ASD.Statement.IfStatement out]
	: IF e=expression THEN statement1=statement FI { $out = new TP2.ASD.Statement.IfStatement($e.out, $statement1.out); }
	;

ifElseState returns [TP2.ASD.Statement.IfElseStatement out]
	: IF e=expression THEN statement1=statement ELSE statement2=statement FI { $out = new TP2.ASD.Statement.IfElseStatement($e.out, $statement1.out, $statement2.out); }
	;

whileState returns [TP2.ASD.Statement.WhileStatement out]
	: WHILE e=expression DO block1=block DONE { $out = new TP2.ASD.Statement.WhileStatement($e.out, $block1.out); }
	;

block returns [TP2.ASD.Statement.Block out]
@init { List<TP2.ASD.StatementInterface> statements = new ArrayList<>(); }
	: AL (s=statement { statements.add($s.out); } )* AR { $out = new TP2.ASD.Statement.Block(statements); }
	;

returnState returns [TP2.ASD.Statement.Return out]
	: RETURN e=expression { $out = new TP2.ASD.Statement.Return($e.out); }
	;

expression returns [TP2.ASD.ExpressionInterface out]
	: p=primary { $out = $p.out; }
    | l=expressionPrioritaire ADD r=expressionPrioritaire  { $out = new TP2.ASD.Expression.AddExpression($l.out, $r.out); }
    | l=expressionPrioritaire SUB r=expressionPrioritaire  { $out = new TP2.ASD.Expression.SubExpression($l.out, $r.out); }
    | eP=expressionPrioritaire { $out = $eP.out; }
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

primary returns [TP2.ASD.Expression.IntegerExpression out]
    : INTEGER { $out = new TP2.ASD.Expression.IntegerExpression($INTEGER.int); }
    ;
    
type returns [TP2.ASD.TypeInterface out]
	: INT { $out = new TP2.ASD.Types.Int(); }
	| VOID { $out = new TP2.ASD.Types.Void(); }
	;
    
    