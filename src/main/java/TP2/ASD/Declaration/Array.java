package TP2.ASD.Declaration;

import TP2.ASD.DeclarationInterface;
import TP2.ASD.Ret;
import TP2.exceptions.TypeException;

public class Array implements DeclarationInterface
{
    private String ident;
    private int nbSpace;

    public Array(String ident, int nbSpace)
    {
        this.ident = ident;
        this.nbSpace = nbSpace;
    }

    // Pretty-printer
    @Override
    public String pp()
    {
        return this.ident + "[" + this.nbSpace + "]";
    }

    @Override
    public Ret toIR() throws TypeException
    {
        return null;
    }
}
