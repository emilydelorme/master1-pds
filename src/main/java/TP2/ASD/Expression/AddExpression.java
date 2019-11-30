package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.exceptions.TypeException;

// Concrete class for Expression: add case
public class AddExpression implements ExpressionInterface
{
    private ExpressionInterface left;
    private ExpressionInterface right;
    private boolean parenthesis;

    public AddExpression(ExpressionInterface left, ExpressionInterface right)
    {
        this.left = left;
        this.right = right;
        this.parenthesis = false;
    }

    // Pretty-printer
    public String pp()
    {
        String str = "";
        
        if (this.parenthesis)
        {
            str += "(";
        }
        
        str += left.pp() + " + " + right.pp();
        
        if (this.parenthesis)
        {
            str += ")";
        }
        
        return str;
    }

    // IR generation (IR = Représentation intermédiaire)
    public Ret toIR() throws TypeException
    {
        return ExpressionHelper.retExpression(left.toIR(), right.toIR());
    }
}