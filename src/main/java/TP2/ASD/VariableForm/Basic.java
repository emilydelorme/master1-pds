package TP2.ASD.VariableForm;

import TP2.ASD.VariableFormInterface;
import TP2.ASD.Ret;
import TP2.exceptions.TypeException;

public class Basic implements VariableFormInterface
{
    private String ident;

    public Basic(String ident)
    {
        this.ident = ident;
    }

    // Pretty-printer
    @Override
    public String pp()
    {
        return this.ident;
    }

    @Override
    public Ret toIR() throws TypeException
    {
        return null;
    }
}
