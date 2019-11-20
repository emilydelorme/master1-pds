package TP2.ASD.VariableForme;

import TP2.ASD.VariableFormeInterface;
import TP2.ASD.Ret;
import TP2.exceptions.TypeException;

public class Basic implements VariableFormeInterface
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
