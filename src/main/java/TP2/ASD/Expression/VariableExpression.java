package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.TypeRet;
import TP2.ASD.VariableFormInterface;

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

    public TypeRet toIR()
    {
        return null;
    }
}