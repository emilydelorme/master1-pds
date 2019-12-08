package TP2.ASD.Statement.Block.VariableFormDeclaration;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Types.Int;
import TP2.ASD.VariableFormDeclarationInterface;
import TP2.Llvm.Instructions.alloca.AllocaTab;
import TP2.Llvm.Types.LlvmInt;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.exceptions.TypeException;

public class Array implements VariableFormDeclarationInterface
{
    private String ident;
    private int size;

    public Array(String ident, int size)
    {
        this.ident = ident;
        this.size = size;
    }

    @Override
    public String getIdent()
    {
        return this.ident;
    }

    // Pretty-printer
    @Override
    public String pp()
    {
        return this.ident + "[" + this.size + "]";
    }

    @Override
    public GenericRet toIR(SymbolTable symbolTable)
    {
        GenericRet result = new GenericRet();

        result.getIr().appendCode(new AllocaTab(new LlvmInt(), ident, size));
        
        return result;
    }
}
