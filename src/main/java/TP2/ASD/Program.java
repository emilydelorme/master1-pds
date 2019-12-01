package TP2.ASD;

import java.util.List;

import TP2.ASD.Ret.TypeRet;
import TP2.ASD.Types.Void;
import TP2.Llvm.Instruction;
import TP2.Llvm.InstructionHandler;
import TP2.Llvm.Instructions.Return;
import TP2.SymbolTable.SymbolTable;
import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;

public class Program
{
    private List<UnitInterface> unitInterface;
    private SymbolTable symbolTable;

    public Program(List<UnitInterface> unitInterface, SymbolTable symbolTable)
    {
        this.unitInterface = unitInterface;
        this.symbolTable = symbolTable;
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