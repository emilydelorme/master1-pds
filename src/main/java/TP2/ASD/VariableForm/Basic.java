package TP2.ASD.VariableForm;

import TP2.ASD.Ret.GenericRet;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.ASD.VariableFormInterface;
import TP2.exceptions.TypeException;

public class Basic implements VariableFormInterface
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
    
    @Override
    public void checkError()
    {
        Symbol symbol = this.symbolTable.lookup(this.ident);
        
        if (!(symbol instanceof VariableSymbol))
        {
            exitWithMessage(String.format("[Variable] (%s) unknown variable", this.ident));
        }
    }
    
    @Override
    public String pp()
    {
        checkError();
        
        return this.ident;
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return new GenericRet();
    }
}
