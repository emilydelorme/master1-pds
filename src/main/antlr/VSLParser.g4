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
@init { 
	List<TP2.ASD.UnitInterface> units = new ArrayList<>();
	TP2.SymbolTable.SymbolTable symbolTable = new TP2.SymbolTable.SymbolTable();
}
    : (u=unit[symbolTable] { units.add($u.out); } )* EOF { $out = new TP2.ASD.Program(units, symbolTable); }
    ;

// =====================================================================
// ---------- PROTOTYPE/FUNCTION
// =====================================================================

unit[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.UnitInterface out]
	: p=prototype[symbolTable] { $out = $p.out; }
	| f=function[symbolTable] { $out = $f.out; }
	;

prototype[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Unit.Prototype out]
@init { 
	List<TP2.SymbolTable.VariableSymbol> arguments = new ArrayList<>();
}
	: PROTO t=functionType IDENT LP p=parameters[arguments] RP { 
		$symbolTable.add(new TP2.SymbolTable.PrototypeSymbol($t.out, $IDENT.text, arguments, false));
		$out = new TP2.ASD.Unit.Prototype($t.out, $IDENT.text, $p.out);
	}
	;

function[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Unit.Function out]
@init { 
	List<TP2.SymbolTable.VariableSymbol> arguments = new ArrayList<>();
}
	: FUNC t=functionType IDENT LP p=parameters[arguments] RP s=statement[symbolTable] {
		$symbolTable.add(new TP2.SymbolTable.FunctionSymbol($t.out, $IDENT.text, arguments));
		$out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $s.out);
	}
	| FUNC t=functionType IDENT LP p=parameters[arguments] RP b=block[symbolTable] {
		$symbolTable.add(new TP2.SymbolTable.FunctionSymbol($t.out, $IDENT.text, arguments));
		$out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $b.out);
	}
	;

// =====================================================================
// ---------- PARAMETERS (function parameter Basic/Array)
// =====================================================================

parameters[List<TP2.SymbolTable.VariableSymbol> arguments] returns [List<TP2.ASD.ParameterInterface> out]
@init { 
	List<TP2.ASD.ParameterInterface> parametres = new ArrayList<>();
}
	: (p=parameterForm[arguments] { parametres.add($p.out); } )? { $out = parametres; }
	| (p=parameterForm[arguments] { parametres.add($p.out); } ) (VIRGULE p2=parameterForm[arguments] { parametres.add($p2.out); })+ { $out = parametres; }
	;

parameterForm[List<TP2.SymbolTable.VariableSymbol> arguments] returns [TP2.ASD.ParameterInterface out]
	: b=parameterBasic[arguments] { $out = $b.out; }
	| a=parameterArray[arguments] { $out = $a.out; }
	;

parameterBasic[List<TP2.SymbolTable.VariableSymbol> arguments] returns [TP2.ASD.Parameter.Basic out]
	: IDENT { 
		$arguments.add(new TP2.SymbolTable.VariableSymbol(new TP2.ASD.Types.Int(), $IDENT.text, false));
		$out = new TP2.ASD.Parameter.Basic($IDENT.text);
	}
	;

parameterArray[List<TP2.SymbolTable.VariableSymbol> arguments] returns [TP2.ASD.Parameter.Array out]
	: IDENT CL CR { 
		$arguments.add(new TP2.SymbolTable.VariableSymbol(new TP2.ASD.Types.Int(), $IDENT.text, true));
		$out = new TP2.ASD.Parameter.Array($IDENT.text);
	}
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

statement[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.StatementInterface out]
    : a=affectation[symbolTable] { $out = $a.out; }
    | ifS=ifState[symbolTable] { $out = $ifS.out; }
    | ifES=ifElseState[symbolTable] { $out = $ifES.out; }
    | w=whileState[symbolTable] { $out = $w.out; }
    | b=block[symbolTable] { $out = $b.out; }
    | p=print[symbolTable] { $out = $p.out; }
    | r1=read[symbolTable] { $out = $r1.out; }
    | f=funcCall[symbolTable] { $out = $f.out; }
    | r2=returnState[symbolTable] { $out = $r2.out; }
	;

// ------------------------------------
// ## affectation
// ------------------------------------

affectation[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.Affectation out]
    : v=variableForm[symbolTable] EQUAL e=expression[symbolTable]  { $out = new TP2.ASD.Statement.Affectation($v.out, $e.out); }
    ;

// ------------------------------------
// ## if then fi
// ------------------------------------

ifState[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.IfStatement out]
	: IF e=expression[symbolTable] THEN statement1=statement[symbolTable] FI { $out = new TP2.ASD.Statement.IfStatement($e.out, $statement1.out); }
	;

// ------------------------------------
// ## if then else fi
// ------------------------------------

ifElseState[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.IfElseStatement out]
	: IF e=expression[symbolTable] THEN statement1=statement[symbolTable] ELSE statement2=statement[symbolTable] FI { $out = new TP2.ASD.Statement.IfElseStatement($e.out, $statement1.out, $statement2.out); }
	;

// ------------------------------------
// ## while do done
// ------------------------------------

whileState[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.WhileStatement out]
	: WHILE e=expression[symbolTable] DO statement1=statement[symbolTable] DONE { $out = new TP2.ASD.Statement.WhileStatement($e.out, $statement1.out); }
	;

// ------------------------------------
// ## block
// ------------------------------------

block[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.Block.Block out]
@init {
	List<TP2.ASD.StatementInterface> statements = new ArrayList<>();
	TP2.SymbolTable.SymbolTable symbolTableBlock = new TP2.SymbolTable.SymbolTable();
	symbolTableBlock.setParent(symbolTable);
}
	: AL (d=declaration[symbolTableBlock]) (s=statement[symbolTableBlock] { statements.add($s.out); })+ AR { $out = new TP2.ASD.Statement.Block.Block(Optional.of($d.out), statements, symbolTableBlock); }
	| AL (s=statement[symbolTableBlock] { statements.add($s.out); })+ AR { $out = new TP2.ASD.Statement.Block.Block(statements, symbolTableBlock); }
	;

// ******** DECLARATION ********

declaration[TP2.SymbolTable.SymbolTable symbolTableBlock] returns [TP2.ASD.Statement.Block.Declaration out]
@init { 
	List<TP2.ASD.VariableFormDeclarationInterface> variablesFormDeclaration = new ArrayList<>();
}
    : t=variableType (v=variableFormDeclaration[$t.out, symbolTableBlock] { variablesFormDeclaration.add($v.out); }) { $out = new TP2.ASD.Statement.Block.Declaration($t.out, variablesFormDeclaration); }
    | t=variableType (v=variableFormDeclaration[$t.out, symbolTableBlock] { variablesFormDeclaration.add($v.out); }) (VIRGULE v=variableFormDeclaration[$t.out, symbolTableBlock] { variablesFormDeclaration.add($v.out); })+ { $out = new TP2.ASD.Statement.Block.Declaration($t.out, variablesFormDeclaration); }
    ;

// ------------------------------------
// ## print
// ------------------------------------

print[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.Print out]
@init { List<TP2.ASD.ItemInterface> items = new ArrayList<>(); }
	: PRINT (i=item[symbolTable] { items.add($i.out); }) (VIRGULE i=item[symbolTable] { items.add($i.out); })* { $out = new TP2.ASD.Statement.Print(items); }
	;

// ******** ITEM ********

item[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.ItemInterface out]
	: iE=itemExpression[symbolTable] { $out = $iE.out; }
	| iT=itemText { $out = $iT.out; }
	;

itemExpression[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Item.Expression out]
	: e=expression[symbolTable] { $out = new TP2.ASD.Item.Expression($e.out); }
	;

itemText returns [TP2.ASD.Item.Text out]
	: TEXT { $out = new TP2.ASD.Item.Text($TEXT.text); }
	;

// ------------------------------------
// ## read
// ------------------------------------

read[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.Read out]
@init { List<TP2.ASD.VariableFormInterface> variablesForm = new ArrayList<>(); }
	: READ (v=variableForm[symbolTable] { variablesForm.add($v.out); }) (VIRGULE v=variableForm[symbolTable] { variablesForm.add($v.out); })* { $out = new TP2.ASD.Statement.Read(variablesForm); }
	;

// ------------------------------------
// ## function call
// ------------------------------------

funcCall[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.FunctionCall out]
@init { List<TP2.ASD.ExpressionInterface> expressions = new ArrayList<>(); }
	: IDENT LP (e=expression[symbolTable] { expressions.add($e.out); })? RP { $out = new TP2.ASD.Statement.FunctionCall($IDENT.text, expressions, $symbolTable); }
	| IDENT LP (e=expression[symbolTable] { expressions.add($e.out); }) (VIRGULE e=expression[symbolTable] { expressions.add($e.out); })+ RP { $out = new TP2.ASD.Statement.FunctionCall($IDENT.text, expressions, $symbolTable); }
	;

// ------------------------------------
// ## return
// ------------------------------------

returnState[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.Return out]
	: RETURN e=expression[symbolTable] { $out = new TP2.ASD.Statement.Return($e.out); }
	;

// =====================================================================
// ---------- EXPRESSION
// =====================================================================

/*
 // https://github.com/antlr/antlr4/blob/master/doc/parser-rules.md
 // https://stackoverflow.com/questions/44531576/why-does-antlr-require-all-or-none-alternatives-be-labeled

expression[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.ExpressionInterface out]
	: l=atomicExpression[symbolTable] MUL r=expression[symbolTable] { $out = new TP2.ASD.Expression.MulExpression($l.out, $r.out); }
	| l=atomicExpression[symbolTable] DIV r=expression[symbolTable] { $out = new TP2.ASD.Expression.DivExpression($l.out, $r.out); }
	| l=atomicExpression[symbolTable] ADD r=expression[symbolTable] { $out = new TP2.ASD.Expression.AddExpression($l.out, $r.out); }
	| l=atomicExpression[symbolTable] SUB r=expression[symbolTable] { $out = new TP2.ASD.Expression.SubExpression($l.out, $r.out); }
	| LP e=expression[symbolTable] RP { $out = $e.out; }
	| at=atomicExpression[symbolTable] { $out = $at.out; }
	;
*/

expression[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.ExpressionInterface out]
    : f=factor[symbolTable] { $out = $f.out; }
    | l=factor[symbolTable] ADD r=expression[symbolTable] { $out = new TP2.ASD.Expression.AddExpression($l.out, $r.out); }
    | l=factor[symbolTable] SUB r=expression[symbolTable] { $out = new TP2.ASD.Expression.SubExpression($l.out, $r.out); }
    ;

factor[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.ExpressionInterface out]
    : p=primary[symbolTable] { $out = $p.out; }
    | p=primary[symbolTable] MUL f=factor[symbolTable] { $out= new TP2.ASD.Expression.MulExpression($p.out,$f.out); }
    | p=primary[symbolTable] DIV f=factor[symbolTable] { $out= new TP2.ASD.Expression.DivExpression($p.out,$f.out); }
    ;

primary[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.ExpressionInterface out]
    : at=atomicExpression[symbolTable] { $out = $at.out; }
    | LP e=expression[symbolTable] RP { $out = $e.out; }
    ;

// ******** ATOMIC EXPRESSION ********

atomicExpression[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.ExpressionInterface out]
    : i=integerExpression { $out = $i.out; }
    | v=variableExpression[symbolTable] { $out = $v.out; }
    | f=funcCall[symbolTable] { $out = $f.out; }
    ;

integerExpression returns [TP2.ASD.Expression.IntegerExpression out]
    : INTEGER { $out = new TP2.ASD.Expression.IntegerExpression($INTEGER.int); }
    ;

variableExpression[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Expression.VariableExpression out]
    : v=variableForm[symbolTable] { $out = new TP2.ASD.Expression.VariableExpression($v.out, $symbolTable); }
    ;

// =====================================================================
// ---------- FORM OF VARIABLE ON USE (Basic/Array)
// =====================================================================

variableForm[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.VariableFormInterface out]
	: b=basicForm { $out = $b.out; }
	| a=arrayForm[symbolTable] { $out = $a.out; }
	;

basicForm returns [TP2.ASD.VariableForm.Basic out]
	: IDENT { $out = new TP2.ASD.VariableForm.Basic($IDENT.text); }
	;

arrayForm[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.VariableForm.Array out]
	: IDENT CL e=expression[symbolTable] CR { $out = new TP2.ASD.VariableForm.Array($IDENT.text, $e.out); }
	;

// =====================================================================
// ---------- FORM OF VARIABLE ON DECLARATION (Basic/Array)
// =====================================================================

variableFormDeclaration[TP2.ASD.TypeInterface type, TP2.SymbolTable.SymbolTable symbolTableBlock] returns [TP2.ASD.VariableFormDeclarationInterface out]
	: b=basicFormDeclaration[type, symbolTableBlock] { $out = $b.out; }
	| a=arrayFormDeclaration[type, symbolTableBlock] { $out = $a.out; }
	;

basicFormDeclaration[TP2.ASD.TypeInterface type, TP2.SymbolTable.SymbolTable symbolTableBlock] returns [TP2.ASD.Statement.Block.VariableFormDeclaration.Basic out]
	: IDENT {
		$symbolTableBlock.add(new TP2.SymbolTable.VariableSymbol($type, $IDENT.text, false));
		$out = new TP2.ASD.Statement.Block.VariableFormDeclaration.Basic($IDENT.text);
	}
	;

arrayFormDeclaration[TP2.ASD.TypeInterface type, TP2.SymbolTable.SymbolTable symbolTableBlock] returns [TP2.ASD.Statement.Block.VariableFormDeclaration.Array out]
	: IDENT CL INTEGER CR {
		$symbolTableBlock.add(new TP2.SymbolTable.VariableSymbol($type, $IDENT.text, true));
		$out = new TP2.ASD.Statement.Block.VariableFormDeclaration.Array($IDENT.text, $INTEGER.int);
	}
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