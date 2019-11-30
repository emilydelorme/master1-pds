package TP2.ASD.Statement;

import java.util.List;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.TypeRet;
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
        StringBuilder str = new StringBuilder(this.funcIdent);
        
        str.append("(");
        
        int variablesSize = this.expressions.size();
        
        for(int i = 0; i < variablesSize; ++i)
        {

            str.append(this.expressions.get(i).pp());

            if (i < variablesSize - 1)
            {
                str.append(", ");
            }
        }
        
        str.append(")");
        
        return str.toString();
    }

    @Override
    public TypeRet toIR() throws TypeException
    {
        return null;
    }

}