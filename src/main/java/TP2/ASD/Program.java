package TP2.ASD;

import java.util.ArrayList;

import TP2.Llvm;
import TP2.TypeException;

public class Program
{
    ArrayList<Expression> expressions; // What a program contains. TODO : change when you extend the language

    public Program(ArrayList<Expression> expressions)
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
    public Llvm.IR toIR() throws TypeException
    {
        // TODO : change when you extend the language

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