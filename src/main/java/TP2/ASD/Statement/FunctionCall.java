package TP2.ASD.Statement;

import java.util.List;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class FunctionCall implements StatementInterface, ExpressionInterface
{
    private String funcIdent;
    private List<ExpressionInterface> expressions;

    public FunctionCall(String funcIdent, List<ExpressionInterface> expressions)
    {
        this.funcIdent = funcIdent;
        this.expressions = expressions;
    }

    @Override
    public String pp()
    {
        String str = this.funcIdent;
        
        str += "(";
        
        int variablesSize = this.expressions.size();
        
        for(int i = 0; i < variablesSize; ++i)
        {

            str += this.expressions.get(i).pp();

            if (i < variablesSize - 1)
            {
                str += ", ";
            }
        }
        
        str += ")";
        
        return str;
    }

    @Override
    public Ret toIR() throws TypeException
    {
        return null;
    }

}