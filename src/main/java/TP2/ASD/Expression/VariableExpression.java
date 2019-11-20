package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.ASD.VariableFormeInterface;
import TP2.ASD.Types.Int;
import TP2.Llvm;

public class VariableExpression implements ExpressionInterface
{
    private VariableFormeInterface variable;

    public VariableExpression(VariableFormeInterface variable)
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