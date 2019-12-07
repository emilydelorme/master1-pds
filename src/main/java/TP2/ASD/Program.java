package TP2.ASD;

import java.util.List;

import TP2.ASD.Ret.TypeRet;
import TP2.ASD.Types.Void;
import TP2.ASD.Unit.Function;
import TP2.Llvm.Instruction;
import TP2.Llvm.InstructionHandler;
import TP2.Llvm.Instructions.Return;
import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;

public class Program
{
    private List<UnitInterface> unitInterface;
    private static final String MAIN_FUNCTION_NAME = "main";

    private Program(List<UnitInterface> unitInterface)
    {
        this.unitInterface = unitInterface;
    }
    
    public static Program create(List<UnitInterface> unitInterface)
    {
        Function mainFunction = null;
        
        for (UnitInterface unit : unitInterface)
        {
            if (unit instanceof Function && unit.getIdent().equals(MAIN_FUNCTION_NAME))
            {
                mainFunction = (Function)unit;
            }
        }
        
        if (mainFunction == null)
        {
            System.err.println(String.format("ERROR: [Program] (%s) function not detected", MAIN_FUNCTION_NAME));
            
            System.exit(1);
        }
        
        if (!mainFunction.getArguments().isEmpty())
        {
            System.err.println(String.format("ERROR: [Program] (%s) function shouldn't have any arguments", MAIN_FUNCTION_NAME));
            
            System.exit(1);
        }
        
        for (UnitInterface unit : unitInterface)
        {
            unit.checkError();
        }
        
        return new Program(unitInterface);
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
    public InstructionHandler toIR() throws TypeException, EmptyProgram
    {
        if (this.unitInterface.isEmpty())
            throw new EmptyProgram("Programme vide");

        TypeRet retExpr = new TypeRet(this.unitInterface.get(0).toIR(), new Void());
        this.unitInterface.remove(0);

        for (UnitInterface unit : this.unitInterface)
        {
            retExpr.getIr().appendAll(unit.toIR().getIr());
        }

        // add a return instruction
        Instruction ret = new Return(retExpr.getType().toLlvmType(), retExpr.getResult());
        retExpr.getIr().appendCode(ret);

        return retExpr.getIr();
    }
}