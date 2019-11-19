package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.ASD.Types.Int;
import TP2.Llvm;

// Concrete class for Expression: constant (integer) case
public class VariableExpression implements ExpressionInterface
{
    private String variable;

    public VariableExpression(String variable)
    {
        this.variable = variable;
    }

    public String pp()
    {
        return "" + this.variable;
    }

    public Ret toIR()
    {
        return null;
    }
}