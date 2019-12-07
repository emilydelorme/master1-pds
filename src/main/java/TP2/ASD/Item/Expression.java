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
    public void checkError()
    {
        this.expression.checkError();
    }

    @Override
    public String pp()
    {
        checkError();
        
        return this.expression.pp();
    }

    @Override
    public GenericRet toIR()
    {
        checkError();
        
        return null;
    }
}
