package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Ret.TypeRet;
import TP2.Llvm.Instructions.Operations.Operation;
import TP2.exceptions.TypeException;

// Concrete class for Expression: mul case
public class MulExpression implements ExpressionInterface
{
    private ExpressionInterface left;
    private ExpressionInterface right;

    public MulExpression(ExpressionInterface left, ExpressionInterface right)
    {
        this.left = left;
        this.right = right;
    }

    // Pretty-printer
    public String pp()
    {
        return left.pp() + " * " + right.pp();
    }

    // IR generation (IR = Représentation intermédiaire)
    public TypeRet toIR() throws TypeException
    {
        return ExpressionHelper.retExpression(left.toIR(), right.toIR(), Operation.MUL);
    }
}