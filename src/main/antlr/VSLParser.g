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

// Liste d'instruction et PAS d'expression, A CHANGER 
program returns [TP2.ASD.Program out]
	@init { ArrayList<TP2.ASD.Expression> expressions = new ArrayList<>(); }
    : (e=expression { expressions.add($e.out); })* EOF { $out = new TP2.ASD.Program(expressions); }
    // TODO : change when you extend the language
    ;

expression returns [TP2.ASD.Expression out]
    : l=expressionPrioritaire ADD r=expressionPrioritaire  { $out = new TP2.ASD.AddExpression($l.out, $r.out); }
    | l=expressionPrioritaire SUB r=expressionPrioritaire  { $out = new TP2.ASD.SubExpression($l.out, $r.out); }
    | type (IDENT) (VIRGULE IDENT)*
    //| IDENT EQUAL l=expression  { $out = new TP2.ASD.Affectation($IDENT.text, $l.out); }
    ;

expressionPrioritaire returns [TP2.ASD.Expression out]
    : l=factor MUL r=factor  { $out = new TP2.ASD.MulExpression($l.out, $r.out); }
    | l=factor DIV r=factor  { $out = new TP2.ASD.DivExpression($l.out, $r.out); }
    | f=factor { $out = $f.out; }
    ;

factor returns [TP2.ASD.Expression out]
    : p=primary { $out = $p.out; }
    | LP e=expression RP { $out = $e.out; }
    // TODO : that's all?
    ;

type
	: INT
	;

primary returns [TP2.ASD.Expression out]
    : INTEGER { $out = new TP2.ASD.IntegerExpression($INTEGER.int); }
    // TODO : that's all?
    ;
