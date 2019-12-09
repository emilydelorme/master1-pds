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
    : (u=unit[symbolTable] { units.add($u.out); } )* EOF { $out = TP2.ASD.Program.create(units, symbolTable); }
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
	TP2.SymbolTable.SymbolTable symbolTableWithArgs = new TP2.SymbolTable.SymbolTable();
	symbolTableWithArgs.setParent($symbolTable);
}
	: PROTO t=functionType IDENT LP p=parameters[arguments, symbolTableWithArgs] RP {
		if (!$symbolTable.add(new TP2.SymbolTable.PrototypeSymbol($t.out, $IDENT.text, arguments, false)))
		{
			System.err.println(String.format("ERROR: [Function prototype] (%s) already exists", $IDENT.text));
        
        	System.exit(1);
		}
		
		$out = new TP2.ASD.Unit.Prototype($t.out, $IDENT.text, $p.out);
	}
	;

function[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Unit.Function out]
@init { 
	List<TP2.SymbolTable.VariableSymbol> arguments = new ArrayList<>();
	TP2.SymbolTable.SymbolTable symbolTableWithArgs = new TP2.SymbolTable.SymbolTable();
	symbolTableWithArgs.setParent($symbolTable);
}
	: FUNC t=functionType IDENT LP p=parameters[arguments, symbolTableWithArgs] RP s=statement[symbolTableWithArgs] {
		if (!$symbolTable.add(new TP2.SymbolTable.FunctionSymbol($t.out, $IDENT.text, arguments)))
		{
			System.err.println(String.format("ERROR: [Function definition] (%s) already defined", $IDENT.text));
        
        	System.exit(1);
		}

		$out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $s.out, symbolTableWithArgs);
	}
	| FUNC t=functionType IDENT LP p=parameters[arguments, symbolTableWithArgs] RP b=block[symbolTableWithArgs] {
		if (!$symbolTable.add(new TP2.SymbolTable.FunctionSymbol($t.out, $IDENT.text, arguments)))
		{
			System.err.println(String.format("ERROR: [Function definition] (%s) already defined", $IDENT.text));
        
        	System.exit(1);
		}
		
		$out = new TP2.ASD.Unit.Function($t.out, $IDENT.text, $p.out, $b.out, symbolTableWithArgs);
	}
	;

// =====================================================================
// ---------- PARAMETERS (function parameter Basic/Array)
// =====================================================================

parameters[List<TP2.SymbolTable.VariableSymbol> arguments, TP2.SymbolTable.SymbolTable symbolTableWithArgs] returns [List<TP2.ASD.ParameterInterface> out]
@init { 
	List<TP2.ASD.ParameterInterface> parametres = new ArrayList<>();
}
	: (p=parameterForm[arguments, symbolTableWithArgs] { parametres.add($p.out); } )? { $out = parametres; }
	| (p=parameterForm[arguments, symbolTableWithArgs] { parametres.add($p.out); } ) (VIRGULE p2=parameterForm[arguments, symbolTableWithArgs] { parametres.add($p2.out); })+ { $out = parametres; }
	;

parameterForm[List<TP2.SymbolTable.VariableSymbol> arguments, TP2.SymbolTable.SymbolTable symbolTableWithArgs] returns [TP2.ASD.ParameterInterface out]
	: b=parameterBasic[arguments, symbolTableWithArgs] { $out = $b.out; }
	| a=parameterArray[arguments, symbolTableWithArgs] { $out = $a.out; }
	;

parameterBasic[List<TP2.SymbolTable.VariableSymbol> arguments, TP2.SymbolTable.SymbolTable symbolTableWithArgs] returns [TP2.ASD.Parameter.Basic out]
	: IDENT {
		//TODO: Change if we add types
		if (!$symbolTableWithArgs.add(new TP2.SymbolTable.VariableSymbol(new TP2.ASD.Types.Int(), $IDENT.text, false)))
		{
			System.err.println(String.format("ERROR: [Variable declaration] (%s) already declared", $IDENT.text));
        
        	System.exit(1);
		}
		
		$arguments.add(new TP2.SymbolTable.VariableSymbol(new TP2.ASD.Types.Int(), $IDENT.text, false));
		$out = new TP2.ASD.Parameter.Basic($IDENT.text);
	}
	;

parameterArray[List<TP2.SymbolTable.VariableSymbol> arguments, TP2.SymbolTable.SymbolTable symbolTableWithArgs] returns [TP2.ASD.Parameter.Array out]
	: IDENT CL CR {
		if (!$symbolTableWithArgs.add(new TP2.SymbolTable.VariableSymbol(new TP2.ASD.Types.Int(), $IDENT.text, true)))
		{
			System.err.println(String.format("ERROR: [Variable declaration] (%s) already declared", $IDENT.text));
        
        	System.exit(1);
		}
		
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
    : v=variableForm[symbolTable, false] EQUAL e=expression[symbolTable, false]  { $out = new TP2.ASD.Statement.Affectation($v.out, $e.out, $symbolTable); }
    ;

// ------------------------------------
// ## if then fi
// ------------------------------------

ifState[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.IfStatement out]
	: IF e=expression[symbolTable, false] THEN statement1=statement[symbolTable] FI { $out = new TP2.ASD.Statement.IfStatement($e.out, $statement1.out); }
	;

// ------------------------------------
// ## if then else fi
// ------------------------------------

ifElseState[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.IfElseStatement out]
	: IF e=expression[symbolTable, false] THEN statement1=statement[symbolTable] ELSE statement2=statement[symbolTable] FI { $out = new TP2.ASD.Statement.IfElseStatement($e.out, $statement1.out, $statement2.out); }
	;

// ------------------------------------
// ## while do done
// ------------------------------------

whileState[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.WhileStatement out]
	: WHILE e=expression[symbolTable, false] DO statement1=statement[symbolTable] DONE { $out = new TP2.ASD.Statement.WhileStatement($e.out, $statement1.out); }
	;

// ------------------------------------
// ## block
// ------------------------------------

block[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.Block.Block out]
@init {
	List<TP2.ASD.StatementInterface> statements = new ArrayList<>();
	List<TP2.ASD.Statement.Block.Declaration> declarations = new ArrayList<>();
	TP2.SymbolTable.SymbolTable symbolTableBlock = new TP2.SymbolTable.SymbolTable();
	symbolTableBlock.setParent(symbolTable);
}
	: AL (d=declaration[symbolTableBlock] { declarations.add($d.out); })+ (s=statement[symbolTableBlock] { statements.add($s.out); })+ AR { $out = new TP2.ASD.Statement.Block.Block(declarations, statements); }
	| AL (s=statement[symbolTableBlock] { statements.add($s.out); })+ AR { $out = new TP2.ASD.Statement.Block.Block(statements); }
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
	: e=expression[symbolTable, false] { $out = new TP2.ASD.Item.Expression($e.out); }
	;

itemText returns [TP2.ASD.Item.Text out]
	: TEXT { $out = new TP2.ASD.Item.Text($TEXT.text); }
	;

// ------------------------------------
// ## read
// ------------------------------------

read[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.Read out]
@init { List<TP2.ASD.VariableFormInterface> variablesForm = new ArrayList<>(); }
	: READ (v=variableForm[symbolTable, false] { variablesForm.add($v.out); }) (VIRGULE v=variableForm[symbolTable, false] { variablesForm.add($v.out); })* { $out = new TP2.ASD.Statement.Read(variablesForm); }
	;

// ------------------------------------
// ## function call
// ------------------------------------

funcCall[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.FunctionCall out]
@init { List<TP2.ASD.ExpressionInterface> expressions = new ArrayList<>(); }
	: IDENT LP (e=expression[symbolTable, true] { expressions.add($e.out); })? RP { $out = new TP2.ASD.Statement.FunctionCall($IDENT.text, expressions, $symbolTable); }
	| IDENT LP (e=expression[symbolTable, true] { expressions.add($e.out); }) (VIRGULE e=expression[symbolTable, true] { expressions.add($e.out); })+ RP { $out = new TP2.ASD.Statement.FunctionCall($IDENT.text, expressions, $symbolTable); }
	;

// ------------------------------------
// ## return
// ------------------------------------

returnState[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Statement.Return out]
	: RETURN e=expression[symbolTable, false] { $out = new TP2.ASD.Statement.Return($e.out, $symbolTable); }
	;

// =====================================================================
// ---------- EXPRESSION
// =====================================================================

/*
 // https://github.com/antlr/antlr4/blob/master/doc/parser-rules.md
 // https://stackoverflow.com/questions/44531576/why-does-antlr-require-all-or-none-alternatives-be-labeled

expression[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.ExpressionInterface out]
	: l=atomicExpression[symbolTable] MUL r=expression[symbolTable, functionCall] { $out = new TP2.ASD.Expression.MulExpression($l.out, $r.out); }
	| l=atomicExpression[symbolTable] DIV r=expression[symbolTable, functionCall] { $out = new TP2.ASD.Expression.DivExpression($l.out, $r.out); }
	| l=atomicExpression[symbolTable] ADD r=expression[symbolTable, functionCall] { $out = new TP2.ASD.Expression.AddExpression($l.out, $r.out); }
	| l=atomicExpression[symbolTable] SUB r=expression[symbolTable, functionCall] { $out = new TP2.ASD.Expression.SubExpression($l.out, $r.out); }
	| LP e=expression[symbolTable, functionCall] RP { $out = $e.out; }
	| at=atomicExpression[symbolTable] { $out = $at.out; }
	;
*/

expression[TP2.SymbolTable.SymbolTable symbolTable, boolean functionCall] returns [TP2.ASD.ExpressionInterface out]
    : f=factor[symbolTable, functionCall] { $out = $f.out; }
    | l=factor[symbolTable, functionCall] ADD r=expression[symbolTable, functionCall] { $out = new TP2.ASD.Expression.AddExpression($l.out, $r.out); }
    | l=factor[symbolTable, functionCall] SUB r=expression[symbolTable, functionCall] { $out = new TP2.ASD.Expression.SubExpression($l.out, $r.out); }
    ;

factor[TP2.SymbolTable.SymbolTable symbolTable, boolean functionCall] returns [TP2.ASD.ExpressionInterface out]
    : p=primary[symbolTable, functionCall] { $out = $p.out; }
    | p=primary[symbolTable, functionCall] MUL f=factor[symbolTable, functionCall] { $out= new TP2.ASD.Expression.MulExpression($p.out,$f.out); }
    | p=primary[symbolTable, functionCall] DIV f=factor[symbolTable, functionCall] { $out= new TP2.ASD.Expression.DivExpression($p.out,$f.out); }
    ;

primary[TP2.SymbolTable.SymbolTable symbolTable, boolean functionCall] returns [TP2.ASD.ExpressionInterface out]
    : at=atomicExpression[symbolTable, functionCall] { $out = $at.out; }
    | LP e=expression[symbolTable, functionCall] RP { $out = $e.out; }
    ;

// ******** ATOMIC EXPRESSION ********

atomicExpression[TP2.SymbolTable.SymbolTable symbolTable, boolean functionCall] returns [TP2.ASD.ExpressionInterface out]
    : i=integerExpression { $out = $i.out; }
    | v=variableExpression[symbolTable, functionCall] { $out = $v.out; }
    | f=funcCall[symbolTable] { $out = $f.out; }
    ;

integerExpression returns [TP2.ASD.Expression.IntegerExpression out]
    : INTEGER { $out = new TP2.ASD.Expression.IntegerExpression($INTEGER.int); }
    ;

variableExpression[TP2.SymbolTable.SymbolTable symbolTable, boolean functionCall] returns [TP2.ASD.Expression.VariableExpression out]
    : v=variableForm[symbolTable, functionCall] { $out = new TP2.ASD.Expression.VariableExpression($v.out, $symbolTable); }
    ;

// =====================================================================
// ---------- FORM OF VARIABLE ON USE (Basic/Array)
// =====================================================================

variableForm[TP2.SymbolTable.SymbolTable symbolTable, boolean functionCall] returns [TP2.ASD.VariableFormInterface out]
	: b=basicForm[symbolTable, functionCall] { $out = $b.out; }
	| a=arrayForm[symbolTable, functionCall] { $out = $a.out; }
	;

basicForm[TP2.SymbolTable.SymbolTable symbolTable, boolean functionCall] returns [TP2.ASD.VariableForm.Basic out]
	: IDENT {
		TP2.SymbolTable.Symbol symbol = $symbolTable.lookup($IDENT.text);
		
		if (symbol instanceof TP2.SymbolTable.VariableSymbol && $functionCall == false)
		{
			TP2.SymbolTable.VariableSymbol variableSymbol = (TP2.SymbolTable.VariableSymbol)symbol;
			
			if (variableSymbol.isArray())
            {
            	System.err.println(String.format("[Affectation] (%s) needs to be called as an array", $IDENT.text));
        
        		System.exit(1);
            }
		}
		
		$out = new TP2.ASD.VariableForm.Basic($IDENT.text, $symbolTable);
	}
	;

arrayForm[TP2.SymbolTable.SymbolTable symbolTable, boolean functionCall] returns [TP2.ASD.VariableForm.Array out]
	: IDENT CL e=expression[symbolTable, false] CR { 
		/*TP2.SymbolTable.Symbol symbol = $symbolTable.lookup($IDENT.text);
		
		if (symbol instanceof TP2.SymbolTable.VariableSymbol && $functionCall == false)
		{
			TP2.SymbolTable.VariableSymbol variableSymbol = (TP2.SymbolTable.VariableSymbol)symbol;
			
			if (!variableSymbol.isArray())
            {
            	System.err.println(String.format("[Affectation] (%s) needs to be a normal variable", $IDENT.text));
        
        		System.exit(1);
            }
		}*/
		
		$out = new TP2.ASD.VariableForm.Array($IDENT.text, $e.out, $symbolTable);
	}
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
		if (!$symbolTableBlock.add(new TP2.SymbolTable.VariableSymbol($type, $IDENT.text, false)))
		{
			System.err.println(String.format("ERROR: [Variable declaration] (%s) already declared", $IDENT.text));
        
        	System.exit(1);
		}

		$out = new TP2.ASD.Statement.Block.VariableFormDeclaration.Basic($IDENT.text, $symbolTableBlock);
	}
	;

arrayFormDeclaration[TP2.ASD.TypeInterface type, TP2.SymbolTable.SymbolTable symbolTableBlock] returns [TP2.ASD.Statement.Block.VariableFormDeclaration.Array out]
	: IDENT CL INTEGER CR {
		if (!$symbolTableBlock.add(new TP2.SymbolTable.VariableSymbol($type, $IDENT.text, true, $INTEGER.int)))
		{
			System.err.println(String.format("ERROR: [Variable declaration] (%s) already declared", $IDENT.text));
        
        	System.exit(1);
		}

		$out = new TP2.ASD.Statement.Block.VariableFormDeclaration.Array($IDENT.text, $INTEGER.int, $symbolTableBlock);
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