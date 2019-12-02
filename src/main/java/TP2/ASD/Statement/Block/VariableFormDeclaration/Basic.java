package TP2.ASD.Statement.Block.VariableFormDeclaration;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.VariableFormDeclarationInterface;
import TP2.exceptions.TypeException;

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
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}
