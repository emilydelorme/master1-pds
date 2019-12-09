package TP2.ASD.Statement.Block.VariableFormDeclaration;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Statement.StatementUtils;
import TP2.ASD.VariableFormDeclarationInterface;
import TP2.Llvm.Instructions.alloca.AllocaVar;
import TP2.Llvm.Types.LlvmInt;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;

public class Basic implements VariableFormDeclarationInterface
{
    private String ident;
    private SymbolTable symbolTable;

    public Basic(String ident, SymbolTable symbolTable)
    {
        this.ident = ident;
        this.symbolTable = symbolTable;
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
    public GenericRet toIR()
    {
        GenericRet result = new GenericRet();

        String llvmIdent = this.ident + "_" + StatementUtils.getCurrentFunction()+ "_" + StatementUtils.getCurrentBlockLevel();

        ((VariableSymbol) symbolTable.lookup(this.ident)).setLlvmIdent(llvmIdent);
        result.getIr().appendCode(new AllocaVar(new LlvmInt(), llvmIdent));
        
        return result;
    }
}
