package TP2.ASD.VariableForm;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.VariableFormInterface;
import TP2.exceptions.TypeException;

public class Basic implements VariableFormInterface
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
