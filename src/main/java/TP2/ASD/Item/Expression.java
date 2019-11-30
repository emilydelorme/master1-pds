package TP2.ASD.Item;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.ItemInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.exceptions.TypeException;

public class Expression implements ItemInterface
{
    private ExpressionInterface expression;

    public Expression(ExpressionInterface expression)
    {
        this.expression = expression;
    }

    @Override
    public String pp()
    {
        return this.expression.pp();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        // TODO Auto-generated method stub
        return null;
    }

}
