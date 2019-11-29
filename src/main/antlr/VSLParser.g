parser grammar VSLParser;

options {
  language = Java;
  tokenVocab = VSLLexer;
}

@header {
  package TP2;

  import java.util.stream.Collectors;
  import java.util.Arrays;
  import java.util.Optional;
}

// =====================================================================
// ---------- PROGRAM
// =====================================================================

program returns [TP2.ASD.Program out]
@init { List<TP2.ASD.UnitInterface> units = new ArrayList<>(); }
    : (u=unit { units.add($u.out); } )* EOF { $out = new TP2.ASD.Program(units); }
    ;

// =====================================================================
// ---------- PROTO/FUNCTION
// =====================================================================

unit returns [TP2.ASD.UnitInterface out]
	: p=prototype { $out = $p.out; }
	| f=function { $out = $f.out; }
	;

prototype returns [TP2.ASD.Unit.Prototype out]
	: PROTO t=funcType IDENT LP p=parameters RP { $out = new TP2.ASD.Unit.Prototype($t.out, $IDENT.text, $p.out); }
	;

function returns [TP2.ASD.Unit.Function out]
	: FUNC t=funcType IDENT LP p=parameters RP s=statement { $out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $s.out); }
	| FUNC t=funcType IDENT LP p=parameters RP b=block { $out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $b.out); }
	;

// =====================================================================
// ---------- PARAMETERS (function parameter Basic/Array)
// =====================================================================

parameters returns [List<TP2.ASD.ParameterInterface> out]
@init { List<TP2.ASD.ParameterInterface> parametres = new ArrayList<>(); }
	: (p=parameterType { parametres.add($p.out); } )? { $out = parametres; }
	| (p=parameterType { parametres.add($p.out); } ) (VIRGULE p2=parameterType { parametres.add($p2.out); })+ { $out = parametres; }
	;

parameterType returns [TP2.ASD.ParameterInterface out]
	: b=parameterBasic { $out = $b.out; }
	| a=parameterArray { $out = $a.out; }
	;
	
parameterBasic returns [TP2.ASD.Parameter.Basic out]
	: IDENT { $out = new TP2.ASD.Parameter.Basic($IDENT.text); }
	;

parameterArray returns [TP2.ASD.Parameter.Array out]
	: IDENT CL CR { $out = new TP2.ASD.Parameter.Array($IDENT.text); }
	;

// =====================================================================
// ---------- STATEMENT
// * affectation
// * if then fi
// * if then else fi
// * while do done
// * block
// * print
// * read
// * function call
// * return
// =====================================================================

statement returns [TP2.ASD.StatementInterface out]
    : a=affectation { $out = $a.out; }
    | ifS=ifState { $out = $ifS.out; }
    | ifES=ifElseState { $out = $ifES.out; }
    | w=whileState { $out = $w.out; }
    | b=block { $out = $b.out; }
    | p=print { $out = $p.out; }
    | r1=read { $out = $r1.out; }
    | f=funcCall { $out = $f.out; }
    | r2=returnState { $out = $r2.out; }
	;

// TODO : chainage des expression non géré, a := 1 + 1 + 1 (rework expression ?)
// modification à faire dans expression ?
affectation returns [TP2.ASD.Statement.Affectation out]
    : v=variableForme EQUAL e=expression  { $out = new TP2.ASD.Statement.Affectation($v.out, $e.out); }
    ;

ifState returns [TP2.ASD.Statement.IfStatement out]
	: IF e=expression THEN statement1=statement FI { $out = new TP2.ASD.Statement.IfStatement($e.out, $statement1.out); }
	;

ifElseState returns [TP2.ASD.Statement.IfElseStatement out]
	: IF e=expression THEN statement1=statement ELSE statement2=statement FI { $out = new TP2.ASD.Statement.IfElseStatement($e.out, $statement1.out, $statement2.out); }
	;

whileState returns [TP2.ASD.Statement.WhileStatement out]
	: WHILE e=expression DO statement1=statement DONE { $out = new TP2.ASD.Statement.WhileStatement($e.out, $statement1.out); }
	;

block returns [TP2.ASD.Statement.Block.Block out]
@init { List<TP2.ASD.StatementInterface> statements = new ArrayList<>(); }
	: AL (d=declaration) (s=statement { statements.add($s.out); })+ AR { $out = new TP2.ASD.Statement.Block.Block(Optional.of($d.out), statements); }
	| AL (s=statement { statements.add($s.out); })+ AR { $out = new TP2.ASD.Statement.Block.Block(statements); }
	;

print returns [TP2.ASD.Statement.Print out]
@init { List<TP2.ASD.ItemInterface> items = new ArrayList<>(); }
	: PRINT (i=item { items.add($i.out); }) (VIRGULE i=item { items.add($i.out); })* { $out = new TP2.ASD.Statement.Print(items); }
	;

item returns [TP2.ASD.ItemInterface out]
	: iE=itemExpression { $out = $iE.out; }
	| iT=itemText { $out = $iT.out; }
	;

itemExpression returns [TP2.ASD.Item.Expression out]
	: e=expression { $out = new TP2.ASD.Item.Expression($e.out); }
	;

itemText returns [TP2.ASD.Item.Text out]
	: TEXT { $out = new TP2.ASD.Item.Text($TEXT.text); }
	;

read returns [TP2.ASD.Statement.Read out]
@init { List<TP2.ASD.VariableFormeInterface> variablesForme = new ArrayList<>(); }
	: READ (v=variableForme { variablesForme.add($v.out); }) (VIRGULE v=variableForme { variablesForme.add($v.out); })* { $out = new TP2.ASD.Statement.Read(variablesForme); }
	;

funcCall returns [TP2.ASD.Statement.FunctionCall out]
@init { List<TP2.ASD.ExpressionInterface> expressions = new ArrayList<>(); }
	: IDENT LP (e=expression { expressions.add($e.out); })? RP { $out = new TP2.ASD.Statement.FunctionCall($IDENT.text, expressions); }
	| IDENT LP (e=expression { expressions.add($e.out); }) (VIRGULE e=expression { expressions.add($e.out); })+ RP { $out = new TP2.ASD.Statement.FunctionCall($IDENT.text, expressions); }
	;

returnState returns [TP2.ASD.Statement.Return out]
	: RETURN e=expression { $out = new TP2.ASD.Statement.Return($e.out); }
	;

// TODO : FAIRE UN INIT ARRAY (array[INT] et non array[expression]) pour la declaration
declaration returns [TP2.ASD.Statement.Block.Declaration out]
@init { List<TP2.ASD.VariableFormeInterface> variablesForme = new ArrayList<>(); }
    : t=varType (v=variableForme { variablesForme.add($v.out); }) { $out = new TP2.ASD.Statement.Block.Declaration($t.out, variablesForme); }
    | t=varType (v=variableForme { variablesForme.add($v.out); }) (VIRGULE v=variableForme { variablesForme.add($v.out); })+ { $out = new TP2.ASD.Statement.Block.Declaration($t.out, variablesForme); }
    ;

variableForme returns [TP2.ASD.VariableFormeInterface out]
	: b=basicForme { $out = $b.out; }
	| a=arrayForme { $out = $a.out; }
	;

basicForme returns [TP2.ASD.VariableForme.Basic out]
	: IDENT { $out = new TP2.ASD.VariableForme.Basic($IDENT.text); }
	;

arrayForme returns [TP2.ASD.VariableForme.Array out]
	: IDENT CL e=expression CR { $out = new TP2.ASD.VariableForme.Array($IDENT.text, $e.out); }
	;

expression returns [TP2.ASD.ExpressionInterface out]
	: t=typeExpr { $out = $t.out; }
	| f=funcCall { $out = $f.out; }
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
    : t=typeExpr { $out = $t.out; }
    | LP e=expression RP { $out = $e.out; }
    ;

typeExpr returns [TP2.ASD.ExpressionInterface out]
    : i=integerExpr { $out = $i.out; }
    | v=varExpr { $out = $v.out; }
    ;

integerExpr returns [TP2.ASD.Expression.IntegerExpression out]
    : INTEGER { $out = new TP2.ASD.Expression.IntegerExpression($INTEGER.int); }
    ;

varExpr returns [TP2.ASD.Expression.VariableExpression out]
    : v=variableForme { $out = new TP2.ASD.Expression.VariableExpression($v.out); }
    ;

funcType returns [TP2.ASD.TypeInterface out]
	: i=intType { $out = $i.out; }
	| v=voidType { $out = $v.out; }
	;

varType returns [TP2.ASD.TypeInterface out]
	: i=intType { $out = $i.out; }
	;
    
intType returns [TP2.ASD.Types.Int out]
	: INT { $out = new TP2.ASD.Types.Int(); }
	;
    
voidType returns [TP2.ASD.Types.Void out]
	: VOID { $out = new TP2.ASD.Types.Void(); }
	;  