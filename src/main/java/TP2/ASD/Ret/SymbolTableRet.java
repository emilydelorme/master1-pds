package TP2.ASD.Ret;

import TP2.Llvm.InstructionHandler;
import TP2.SymbolTable.SymbolTable;

public class SymbolTableRet extends GenericRet
{

    private SymbolTable symbolTable;

    public SymbolTableRet()
    {
        super(new InstructionHandler(), "");
        this.symbolTable = new SymbolTable();
    }

    public SymbolTableRet(InstructionHandler ir, SymbolTable symbolTable)
    {
        super(ir, "");
        this.symbolTable = symbolTable;
    }

    public SymbolTableRet(InstructionHandler ir, String result)
    {
        super(ir, result);
        this.symbolTable = new SymbolTable();
    }

    public SymbolTableRet(InstructionHandler ir, String result, SymbolTable symbolTable)
    {
        super(ir, result);
        this.symbolTable = symbolTable;
    }

    public SymbolTableRet(SymbolTable symbolTable)
    {
        super(new InstructionHandler(), "");
        this.symbolTable = symbolTable;
    }


    public SymbolTable getSymbolTable()
    {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable)
    {
        this.symbolTable = symbolTable;
    }
}
