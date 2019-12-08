package TP2.ASD.Statement.VariableFormAffectation;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.VariableFormDeclarationInterface;
import TP2.Llvm.Instructions.Store;
import TP2.Llvm.Types.LlvmInt;
import TP2.SymbolTable.SymbolTable;

public class Basic implements VariableFormDeclarationInterface
{

    private final String ident;
    private final String pointer;

    public Basic(String ident, String pointer) {
        this.ident = ident;
        this.pointer = pointer;
    }

    @Override
    public String getIdent()
    {
        return ident;
    }

    @Override
    public String pp()
    {
        return null;
    }

    @Override
    public GenericRet toIR(SymbolTable symbolTable)
    {
        GenericRet result = new GenericRet();

        result.getIr().appendCode(new Store(new LlvmInt(), ident, pointer));

        return result;
    }
}
