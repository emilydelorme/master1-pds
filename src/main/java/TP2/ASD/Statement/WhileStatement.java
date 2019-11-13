package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class WhileStatement implements StatementInterface
{

    private ExpressionInterface expressionInterface;
    private Block block;

    public WhileStatement(ExpressionInterface expressionInterface, Block block)
    {
        this.expressionInterface = expressionInterface;
        this.block = block;
    }

    @Override
    public String pp()
    {
        return "WHILE " + expressionInterface.pp() +
                "\n" +
                block.pp() +
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
