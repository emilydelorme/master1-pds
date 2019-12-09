package TP2.ASD.Item;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.ItemInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Ret.TypeRet;
import TP2.exceptions.TypeException;
import org.tinylog.Logger;

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

        GenericRet result = new GenericRet();

        try {
            final TypeRet expressionRet = expression.toIR();
            result.setResult(expressionRet.getResult());
            result.getIr().appendAll(expressionRet.getIr());
        } catch (TypeException e) {
            Logger.error(e.getMessage());
        }

        return result;
    }
}
