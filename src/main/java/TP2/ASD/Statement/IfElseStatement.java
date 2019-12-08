package TP2.ASD.Statement;

import TP2.SymbolTable.SymbolTable;
import TP2.TypeLabel;
import TP2.Utils;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Statement.Block.Block;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class IfElseStatement implements StatementInterface
{
    private ExpressionInterface condition;
    private StatementInterface trueStatement;
    private StatementInterface falseStatement;

    public IfElseStatement(ExpressionInterface condition, StatementInterface trueStatement, StatementInterface falseStatement)
    {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }
    
    @Override
    public void checkError()
    {
        this.condition.checkError();
        this.trueStatement.checkError();
        this.falseStatement.checkError();
    }

    @Override
    public String pp()
    {
        checkError();
        
        //TODO fix indent
        return "IF " + condition.pp() +
                "\n" +
                Utils.indent(Block.identLevel) +
                "THEN" +
                "\n" +
                Utils.indent(Block.identLevel) +
                trueStatement.pp() +
                "\n" +
                Utils.indent(Block.identLevel) +
                "ELSE" +
                "\n" +
                Utils.indent(Block.identLevel) +
                falseStatement.pp() +
                "\n" +
                Utils.indent(Block.identLevel) +
                "FI";
    }

    @Override
    public GenericRet toIR(SymbolTable symbolTable) throws TypeException
    {
        checkError();

        return StatementUtils.createControl(ControlType.ELSEIF, symbolTable,
                Utils.newLabel(TypeLabel.THEN), Utils.newLabel(TypeLabel.FI),
                condition, trueStatement,
                Utils.newLabel(TypeLabel.ELSE), falseStatement);
    }
}
