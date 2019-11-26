package TP2.ASD.VariableForme;

import TP2.ASD.VariableFormeInterface;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.exceptions.TypeException;

public class Array implements VariableFormeInterface
{
    private String ident;
    private ExpressionInterface expression;

    public Array(String ident, ExpressionInterface expression)
    {
        this.ident = ident;
        this.expression = expression;
    }

    // Pretty-printer
    @Override
    public String pp()
    {
        return this.ident + "[" + this.expression.pp() + "]";
    }

    @Override
    public Ret toIR() throws TypeException
    {
        return null;
    }
}
