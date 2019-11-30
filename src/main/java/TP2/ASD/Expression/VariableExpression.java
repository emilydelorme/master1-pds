package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.ASD.VariableFormInterface;
import TP2.ASD.Types.Int;
import TP2.Llvm;

public class VariableExpression implements ExpressionInterface
{
    private VariableFormInterface variable;

    public VariableExpression(VariableFormInterface variable)
    {
        this.variable = variable;
    }

    public String pp()
    {
        return this.variable.pp();
    }

    public Ret toIR()
    {
        return null;
    }
}