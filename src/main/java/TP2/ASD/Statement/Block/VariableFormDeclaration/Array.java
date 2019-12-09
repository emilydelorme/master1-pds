package TP2.ASD.Statement.Block.VariableFormDeclaration;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Statement.StatementUtils;
import TP2.ASD.VariableFormDeclarationInterface;
import TP2.Llvm.Instructions.alloca.AllocaTab;
import TP2.Llvm.Types.LlvmInt;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;

public class Array implements VariableFormDeclarationInterface
{
    private String ident;
    private int size;
    private SymbolTable symbolTable;

    public Array(String ident, int size, SymbolTable symbolTable)
    {
        this.ident = ident;
        this.size = size;
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
        return this.ident + "[" + this.size + "]";
    }

    @Override
    public GenericRet toIR()
    {
        GenericRet result = new GenericRet();

        String llvmIdent = this.ident + "_" + StatementUtils.currentFunction + "_" + StatementUtils.currentBlockLevel;

        ((VariableSymbol) symbolTable.lookup(this.ident)).setLlvmIdent(llvmIdent);
        result.getIr().appendCode(new AllocaTab(new LlvmInt(), llvmIdent, size));
        
        return result;
    }
}
