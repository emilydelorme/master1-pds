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

program returns [TP2.ASD.Program out]
    : e=expression EOF { $out = new TP2.ASD.Program($e.out); } // TODO : change when you extend the language
    ;

expression returns [TP2.ASD.Expression out]
    : l=expressionPrioritaire ADD r=expressionPrioritaire  { $out = new TP2.ASD.AddExpression($l.out, $r.out); }
    | l=expressionPrioritaire SUB r=expressionPrioritaire  { $out = new TP2.ASD.SubExpression($l.out, $r.out); }
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

primary returns [TP2.ASD.Expression out]
    : INTEGER { $out = new TP2.ASD.IntegerExpression($INTEGER.int); }
    // TODO : that's all?
    ;
