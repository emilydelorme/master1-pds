package TP2.ASD.Parameter;

import TP2.ASD.ParameterInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.exceptions.TypeException;

public class Basic implements ParameterInterface
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

    @Override
    public String pp()
    {
        String str = "";
        
        str += this.ident;
        
        return str;
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}
