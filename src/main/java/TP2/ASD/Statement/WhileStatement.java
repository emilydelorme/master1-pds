package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class WhileStatement implements StatementInterface
{

    private ExpressionInterface expression;
    private Block block;

    public WhileStatement(ExpressionInterface expression, Block block)
    {
        this.expression = expression;
        this.block = block;
    }

    @Override
    public String pp()
    {
        return "WHILE " + expression.pp() +
                "\n" +
                block.pp() +
                "\n" +
                "DONE";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}
