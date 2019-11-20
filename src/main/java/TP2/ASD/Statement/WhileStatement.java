package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class WhileStatement implements StatementInterface
{

    private ExpressionInterface expression;
    private StatementInterface statement;

    public WhileStatement(ExpressionInterface expression, StatementInterface statement)
    {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String pp()
    {
        return "WHILE " + expression.pp() +
                "\n" +
                "DO" +
                "\n" +
                statement.pp() +
                "\n" +
                "DONE";
    }

    @Override
    public Ret toIR() throws TypeException
    {
        // TODO: While to IR()
        return null;
    }
}
