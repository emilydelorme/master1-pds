package TP2.ASD.VariableForm;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.VariableFormInterface;
import TP2.ASD.ExpressionInterface;
import TP2.exceptions.TypeException;

public class Array implements VariableFormInterface
{
    private String ident;
    private ExpressionInterface expression;

    public Array(String ident, ExpressionInterface expression)
    {
        this.ident = ident;
        this.expression = expression;
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
        return this.ident + "[" + this.expression.pp() + "]";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}
