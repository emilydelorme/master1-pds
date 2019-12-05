package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class IfElseStatement implements StatementInterface
{

    private ExpressionInterface expression;
    private StatementInterface trueStatement;
    private StatementInterface falseStatement;

    public IfElseStatement(ExpressionInterface expression, StatementInterface trueStatement, StatementInterface falseStatement)
    {
        this.expression = expression;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }
    
    @Override
    public void checkError()
    {
        this.expression.checkError();
        this.trueStatement.checkError();
        this.falseStatement.checkError();
    }

    @Override
    public String pp()
    {
        checkError();
        
        return "IF " + expression.pp() +
                "\n" +
                "THEN" +
                "\n" +
                trueStatement.pp() +
                "\n" +
                "ELSE" +
                "\n" +
                falseStatement.pp() +
                "\n" +
                "FI";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return null;
    }
}
