package TP2.ASD.Statement;

import TP2.Utils;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Statement.Block.Block;
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
    public void checkError()
    {
        this.expression.checkError();
        this.statement.checkError();
    }

    @Override
    public String pp()
    {
        checkError();
        
        //TODO fix indent
        return "IF " + expression.pp() +
                "\n" +
                Utils.indent(Block.identLevel) +
                "THEN" +
                "\n" +
                Utils.indent(Block.identLevel) +
                statement.pp() +
                "\n" +
                Utils.indent(Block.identLevel) +
                "FI";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return null;
    }
}
