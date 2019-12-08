package TP2.ASD.Statement;

import TP2.SymbolTable.SymbolTable;
import TP2.TypeLabel;
import TP2.Utils;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Statement.Block.Block;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class WhileStatement implements StatementInterface
{

    private ExpressionInterface condition;
    private StatementInterface statement;

    public WhileStatement(ExpressionInterface condition, StatementInterface statement)
    {
        this.condition = condition;
        this.statement = statement;
    }
    
    @Override
    public void checkError()
    {
        this.condition.checkError();
        this.statement.checkError();
    }

    @Override
    public String pp()
    {
        checkError();
        
        return "WHILE " + condition.pp() +
                "\n" +
                Utils.indent(Block.identLevel) +
                "DO" +
                "\n" +
                Utils.indent(Block.identLevel) +
                statement.pp() +
                "\n" +
                Utils.indent(Block.identLevel) +
                "DONE";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        return StatementUtils.createControl(ControlType.WHILE, Utils.newLabel(TypeLabel.WHILE), Utils.newLabel(TypeLabel.DONE), condition, statement, Utils.newLabel(TypeLabel.DO), null);
    }
}
