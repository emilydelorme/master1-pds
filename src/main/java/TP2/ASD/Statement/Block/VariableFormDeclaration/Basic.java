package TP2.ASD.Statement.Block.VariableFormDeclaration;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Types.Int;
import TP2.ASD.VariableFormDeclarationInterface;
import TP2.Llvm.Instructions.alloca.AllocaVar;
import TP2.Llvm.Types.LlvmInt;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;

public class Basic implements VariableFormDeclarationInterface
{
    private String ident;

    public Basic(String ident)
    {
        this.ident = ident;
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
        return this.ident;
    }

    @Override
    public GenericRet toIR(SymbolTable symbolTable)
    {
        GenericRet result = new GenericRet();

        result.getIr().appendCode(new AllocaVar(new LlvmInt(), ident));
        
        return result;
    }
}
