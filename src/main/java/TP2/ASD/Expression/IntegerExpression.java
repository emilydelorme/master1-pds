package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.TypeRet;
import TP2.ASD.Types.Int;
import TP2.Llvm.InstructionHandler;

// Concrete class for Expression: constant (integer) case
public class IntegerExpression implements ExpressionInterface
{
    private int value;

    public IntegerExpression(int value)
    {
        this.value = value;
    }

    public String pp()
    {
        return "" + this.value;
    }

    public TypeRet toIR()
    {
        // Here we simply return an empty IR
        // the `result' of this expression is the integer itself (as string)
        return new TypeRet(new InstructionHandler(), "" + value, new Int());
    }
}