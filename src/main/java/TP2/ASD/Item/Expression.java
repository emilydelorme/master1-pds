package TP2.ASD.Item;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.ItemInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Ret.TypeRet;
import TP2.exceptions.TypeException;
import org.tinylog.Logger;

public class Expression implements ItemInterface
{
    private ExpressionInterface expessionValue;

    public Expression(ExpressionInterface expessionValue)
    {
        this.expessionValue = expessionValue;
    }
    
    @Override
    public void checkError()
    {
        this.expessionValue.checkError();
    }

    @Override
    public String pp()
    {
        checkError();
        
        return this.expessionValue.pp();
    }

    @Override
    public GenericRet toIR()
    {
        checkError();

        GenericRet result = new GenericRet();

        try {
            final TypeRet expressionRet = expessionValue.toIR();
            result.setResult(expressionRet.getResult());
            result.getIr().appendAll(expressionRet.getIr());
        } catch (TypeException e) {
            Logger.error(e.getMessage());
        }

        return result;
    }
}
