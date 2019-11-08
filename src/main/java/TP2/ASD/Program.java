package TP2.ASD;

import java.util.ArrayList;
import java.util.List;

import TP2.Llvm;
import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;

public class Program
{
    private List<Expression> expressions;

    public Program(List<Expression> expressions)
    {
        this.expressions = expressions;
    }

    // Pretty-printer
    public String pp()
    {
        return expressions.stream()
                .map(e -> e.pp())
                .reduce((e1, e2) -> e1 + "\n" + e2)
                .orElse("");
    }

    // IR generation
    public Llvm.IR toIR() throws TypeException, EmptyProgram {
        // TODO : change when you extend the language
        if(this.expressions.isEmpty())
            throw new EmptyProgram("Programme vide");


        Expression.RetExpression retExpr = this.expressions.get(0).toIR();
        this.expressions.remove(0);
        
        for(Expression expression : this.expressions)
        {
            retExpr.ir.append(expression.toIR().ir);
        }
        
        // add a return instruction
        Llvm.Instruction ret = new Llvm.Return(retExpr.type.toLlvmType(), retExpr.result);        
        retExpr.ir.appendCode(ret);

        return retExpr.ir;
    }
}