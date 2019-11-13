package TP2.ASD;

import java.util.List;

import TP2.Llvm;
import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;

public class Program
{
    private List<UnitInterface> unitInterface;

    public Program(List<UnitInterface> unitInterface)
    {
        this.unitInterface = unitInterface;
    }

    // Pretty-printer
    public String pp()
    {
        return unitInterface.stream()
                .map(UnitInterface::pp)
                .reduce((e1, e2) -> e1 + "\n" + e2)
                .orElse("");
    }

    // IR generation
    public Llvm.IR toIR() throws TypeException, EmptyProgram {
        // TODO : change when you extend the language
        if(this.unitInterface.isEmpty())
            throw new EmptyProgram("Programme vide");


        Ret retExpr = this.unitInterface.get(0).toIR();
        this.unitInterface.remove(0);
        
        for(UnitInterface unitInterface : this.unitInterface)
        {
            retExpr.ir.append(unitInterface.toIR().ir);
        }
        
        // add a return instruction
        Llvm.Instruction ret = new Llvm.Return(retExpr.type.toLlvmType(), retExpr.result);        
        retExpr.ir.appendCode(ret);

        return retExpr.ir;
    }
}