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
// ---------- PROTOTYPE/FUNCTION
// =====================================================================

unit returns [TP2.ASD.UnitInterface out]
	: p=prototype { $out = $p.out; }
	| f=function { $out = $f.out; }
	;

prototype returns [TP2.ASD.Unit.Prototype out]
	: PROTO t=functionType IDENT LP p=parameters RP { $out = new TP2.ASD.Unit.Prototype($t.out, $IDENT.text, $p.out); }
	;

function returns [TP2.ASD.Unit.Function out]
	: FUNC t=functionType IDENT LP p=parameters RP s=statement { $out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $s.out); }
	| FUNC t=functionType IDENT LP p=parameters RP b=block { $out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $b.out); }
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

// ------------------------------------
// ## affectation
// ------------------------------------

affectation returns [TP2.ASD.Statement.Affectation out]
    : v=variableForm EQUAL e=expression  { $out = new TP2.ASD.Statement.Affectation($v.out, $e.out); }
    ;

// ------------------------------------
// ## if then fi
// ------------------------------------

ifState returns [TP2.ASD.Statement.IfStatement out]
	: IF e=expression THEN statement1=statement FI { $out = new TP2.ASD.Statement.IfStatement($e.out, $statement1.out); }
	;

// ------------------------------------
// ## if then else fi
// ------------------------------------

ifElseState returns [TP2.ASD.Statement.IfElseStatement out]
	: IF e=expression THEN statement1=statement ELSE statement2=statement FI { $out = new TP2.ASD.Statement.IfElseStatement($e.out, $statement1.out, $statement2.out); }
	;

// ------------------------------------
// ## while do done
// ------------------------------------

whileState returns [TP2.ASD.Statement.WhileStatement out]
	: WHILE e=expression DO statement1=statement DONE { $out = new TP2.ASD.Statement.WhileStatement($e.out, $statement1.out); }
	;

// ------------------------------------
// ## block
// ------------------------------------

block returns [TP2.ASD.Statement.Block.Block out]
@init { List<TP2.ASD.StatementInterface> statements = new ArrayList<>(); }
	: AL (d=declaration) (s=statement { statements.add($s.out); })+ AR { $out = new TP2.ASD.Statement.Block.Block(Optional.of($d.out), statements); }
	| AL (s=statement { statements.add($s.out); })+ AR { $out = new TP2.ASD.Statement.Block.Block(statements); }
	;

// ******** DECLARATION ********

declaration returns [TP2.ASD.Statement.Block.Declaration out]
@init { List<TP2.ASD.VariableFormDeclarationInterface> variablesFormDeclaration = new ArrayList<>(); }
    : t=variableType (v=variableFormDeclaration { variablesFormDeclaration.add($v.out); }) { $out = new TP2.ASD.Statement.Block.Declaration($t.out, variablesFormDeclaration); }
    | t=variableType (v=variableFormDeclaration { variablesFormDeclaration.add($v.out); }) (VIRGULE v=variableFormDeclaration { variablesFormDeclaration.add($v.out); })+ { $out = new TP2.ASD.Statement.Block.Declaration($t.out, variablesFormDeclaration); }
    ;

// ------------------------------------
// ## print
// ------------------------------------

print returns [TP2.ASD.Statement.Print out]
@init { List<TP2.ASD.ItemInterface> items = new ArrayList<>(); }
	: PRINT (i=item { items.add($i.out); }) (VIRGULE i=item { items.add($i.out); })* { $out = new TP2.ASD.Statement.Print(items); }
	;

// ******** ITEM ********

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

// ------------------------------------
// ## read
// ------------------------------------

read returns [TP2.ASD.Statement.Read out]
@init { List<TP2.ASD.VariableFormInterface> variablesForm = new ArrayList<>(); }
	: READ (v=variableForm { variablesForm.add($v.out); }) (VIRGULE v=variableForm { variablesForm.add($v.out); })* { $out = new TP2.ASD.Statement.Read(variablesForm); }
	;

// ------------------------------------
// ## function call
// ------------------------------------

funcCall returns [TP2.ASD.Statement.FunctionCall out]
@init { List<TP2.ASD.ExpressionInterface> expressions = new ArrayList<>(); }
	: IDENT LP (e=expression { expressions.add($e.out); })? RP { $out = new TP2.ASD.Statement.FunctionCall($IDENT.text, expressions); }
	| IDENT LP (e=expression { expressions.add($e.out); }) (VIRGULE e=expression { expressions.add($e.out); })+ RP { $out = new TP2.ASD.Statement.FunctionCall($IDENT.text, expressions); }
	;

// ------------------------------------
// ## return
// ------------------------------------

returnState returns [TP2.ASD.Statement.Return out]
	: RETURN e=expression { $out = new TP2.ASD.Statement.Return($e.out); }
	;

// =====================================================================
// ---------- EXPRESSION
// =====================================================================

// https://github.com/antlr/antlr4/blob/master/doc/parser-rules.md
// https://stackoverflow.com/questions/44531576/why-does-antlr-require-all-or-none-alternatives-be-labeled

expression returns [TP2.ASD.ExpressionInterface out]
	: l=expression MUL r=expression { $out = new TP2.ASD.Expression.MulExpression($l.out, $r.out); }	# MultExpr
	| l=expression DIV r=expression { $out = new TP2.ASD.Expression.DivExpression($l.out, $r.out); }	# DivExpr
	| l=expression ADD r=expression { $out = new TP2.ASD.Expression.AddExpression($l.out, $r.out); }	# AddExpr
	| l=expression SUB r=expression { $out = new TP2.ASD.Expression.SubExpression($l.out, $r.out); }	# SubExpr
	| LP e=expression RP { $out = $e.out; }																# ParenthesisExpr
	| at=atomicExpression { $out = $at.out; }															# AtomicExpr
	;

// ******** ATOMIC EXPRESSION ********

atomicExpression returns [TP2.ASD.ExpressionInterface out]
    : i=integerExpression { $out = $i.out; }
    | v=variableExpression { $out = $v.out; }
    | f=funcCall { $out = $f.out; }
    ;

integerExpression returns [TP2.ASD.Expression.IntegerExpression out]
    : INTEGER { $out = new TP2.ASD.Expression.IntegerExpression($INTEGER.int); }
    ;

variableExpression returns [TP2.ASD.Expression.VariableExpression out]
    : v=variableForm { $out = new TP2.ASD.Expression.VariableExpression($v.out); }
    ;

// =====================================================================
// ---------- FORM OF VARIABLE ON USE (Basic/Array)
// =====================================================================

variableForm returns [TP2.ASD.VariableFormInterface out]
	: b=basicForm { $out = $b.out; }
	| a=arrayForm { $out = $a.out; }
	;

basicForm returns [TP2.ASD.VariableForm.Basic out]
	: IDENT { $out = new TP2.ASD.VariableForm.Basic($IDENT.text); }
	;

arrayForm returns [TP2.ASD.VariableForm.Array out]
	: IDENT CL e=expression CR { $out = new TP2.ASD.VariableForm.Array($IDENT.text, $e.out); }
	;

// =====================================================================
// ---------- FORM OF VARIABLE ON DECLARATION (Basic/Array)
// =====================================================================

variableFormDeclaration returns [TP2.ASD.VariableFormDeclarationInterface out]
	: b=basicFormDeclaration { $out = $b.out; }
	| a=arrayFormDeclaration { $out = $a.out; }
	;
	
basicFormDeclaration returns [TP2.ASD.Statement.Block.VariableFormDeclaration.Basic out]
	: IDENT { $out = new TP2.ASD.Statement.Block.VariableFormDeclaration.Basic($IDENT.text); }
	;

arrayFormDeclaration returns [TP2.ASD.Statement.Block.VariableFormDeclaration.Array out]
	: IDENT CL INTEGER CR { $out = new TP2.ASD.Statement.Block.VariableFormDeclaration.Array($IDENT.text, $INTEGER.int); }
	;

// =====================================================================
// ---------- ALLOWED FUNCTION TYPES
// =====================================================================

functionType returns [TP2.ASD.TypeInterface out]
	: i=intType { $out = $i.out; }
	| v=voidType { $out = $v.out; }
	;

// =====================================================================
// ---------- ALLOWED VARIABLE TYPES
// =====================================================================

variableType returns [TP2.ASD.TypeInterface out]
	: i=intType { $out = $i.out; }
	;

// =====================================================================
// ---------- AVAILABLE TYPES
// =====================================================================

intType returns [TP2.ASD.Types.Int out]
	: INT { $out = new TP2.ASD.Types.Int(); }
	;

voidType returns [TP2.ASD.Types.Void out]
	: VOID { $out = new TP2.ASD.Types.Void(); }
	;  