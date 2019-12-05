package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
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
    public void checkError()
    {
        // TODO
        
        this.expression.checkError();
    }
    
    @Override
    public String pp()
    {
        checkError();
        
        return "RETURN" + " " + this.expression.pp();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return null;
    }
}
