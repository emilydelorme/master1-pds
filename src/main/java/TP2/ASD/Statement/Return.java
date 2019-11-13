package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class Return implements StatementInterface
{
    private ExpressionInterface expression;
    
    public Return(ExpressionInterface expression)
    {
        this.expression = expression;
    }
    
    @Override
    public String pp()
    {
        return "RETURN" + " " + this.expression.pp();
    }

    @Override
    public Ret toIR() throws TypeException
    {
        return null;
    }
}
