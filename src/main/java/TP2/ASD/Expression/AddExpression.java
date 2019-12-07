package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.TypeRet;
import TP2.Llvm.Instructions.Operations.Operation;
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
    
    @Override
    public void checkError()
    {
        this.left.checkError();
        this.right.checkError();
    }

    public String pp()
    {
        checkError();
        
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
    public TypeRet toIR() throws TypeException
    {
        checkError();
        
        return ExpressionHelper.retExpression(left.toIR(), right.toIR(), Operation.ADD);
    }
}