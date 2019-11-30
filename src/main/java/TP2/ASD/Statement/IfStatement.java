package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class IfStatement implements StatementInterface
{

    private ExpressionInterface expression;
    private StatementInterface statement;

    public IfStatement(ExpressionInterface expression, StatementInterface statement)
    {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String pp()
    {
        return "IF " + expression.pp() +
                "\n" +
                "THEN" +
                "\n" +
                statement.pp() +
                "\n" +
                "FI";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}
