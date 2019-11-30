package TP2.ASD.Statement.Block.VariableFormDeclaration;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.VariableFormDeclarationInterface;
import TP2.exceptions.TypeException;

public class Array implements VariableFormDeclarationInterface
{
    private String ident;
    private int size;

    public Array(String ident, int size)
    {
        this.ident = ident;
        this.size = size;
    }

    // Pretty-printer
    @Override
    public String pp()
    {
        return this.ident + "[" + this.size + "]";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}
