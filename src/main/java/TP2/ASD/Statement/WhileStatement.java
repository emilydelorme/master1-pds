package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class WhileStatement implements StatementInterface
{

    private ExpressionInterface expression;
    private StatementInterface statementInterface;

    public WhileStatement(ExpressionInterface expression, StatementInterface statementInterface)
    {
        this.expression = expression;
        this.statementInterface = statementInterface;
    }

    @Override
    public String pp()
    {
        return "WHILE " + expression.pp() +
                "\n" +
                "DO" +
                "\n" +
                statementInterface.pp() +
                "\n" +
                "DONE";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}
