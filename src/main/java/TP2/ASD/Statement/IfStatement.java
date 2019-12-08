package TP2.ASD.Statement;

import TP2.ASD.Ret.TypeRet;
import TP2.Llvm.Instructions.Br;
import TP2.Llvm.Instructions.Label;
import TP2.Llvm.Instructions.Operations.CompareToZero;
import TP2.SymbolTable.SymbolTable;
import TP2.TypeLabel;
import TP2.Utils;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Statement.Block.Block;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class IfStatement implements StatementInterface
{

    private ExpressionInterface condition;
    private StatementInterface statement;

    public IfStatement(ExpressionInterface condition, StatementInterface statement)
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
        
        //TODO fix indent
        return "IF " + condition.pp() +
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
        return StatementUtils.createControl(ControlType.IF, Utils.newLabel(TypeLabel.THEN), Utils.newLabel(TypeLabel.FI), condition, statement);
    }

}
