package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

// Concrete class for Expression: add case
public class Affectation implements StatementInterface
{
    private String ident;
    private ExpressionInterface expression;

    public Affectation(String ident, ExpressionInterface expression)
    {
        this.ident = ident;
        this.expression = expression;
    }

    // Pretty-printer
    @Override
    public String pp()
    {
        return this.ident + " := " + expression.pp();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}