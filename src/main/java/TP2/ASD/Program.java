package TP2.ASD;

import java.util.List;

import TP2.Llvm;
import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;

public class Program
{
    private List<ExpressionInterface> expressionInterfaces;

    public Program(List<ExpressionInterface> expressionInterfaces)
    {
        this.expressionInterfaces = expressionInterfaces;
    }

    // Pretty-printer
    public String pp()
    {
        return expressionInterfaces.stream()
                .map(ExpressionInterface::pp)
                .reduce((e1, e2) -> e1 + "\n" + e2)
                .orElse("");
    }

    // IR generation
    public Llvm.IR toIR() throws TypeException, EmptyProgram {
        // TODO : change when you extend the language
        if(this.expressionInterfaces.isEmpty())
            throw new EmptyProgram("Programme vide");


        Ret retExpr = this.expressionInterfaces.get(0).toIR();
        this.expressionInterfaces.remove(0);
        
        for(ExpressionInterface expressionInterface : this.expressionInterfaces)
        {
            retExpr.ir.append(expressionInterface.toIR().ir);
        }
        
        // add a return instruction
        Llvm.Instruction ret = new Llvm.Return(retExpr.type.toLlvmType(), retExpr.result);        
        retExpr.ir.appendCode(ret);

        return retExpr.ir;
    }
}