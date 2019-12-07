package TP2.ASD.Statement;

import TP2.SymbolTable.SymbolTable;
import TP2.Utils;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Statement.Block.Block;
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
    public void checkError()
    {
        this.expression.checkError();
        this.statementInterface.checkError();
    }

    @Override
    public String pp()
    {
        checkError();
        
        return "WHILE " + expression.pp() +
                "\n" +
                Utils.indent(Block.identLevel) +
                "DO" +
                "\n" +
                Utils.indent(Block.identLevel) +
                statementInterface.pp() +
                "\n" +
                Utils.indent(Block.identLevel) +
                "DONE";
    }

    @Override
    public GenericRet toIR(SymbolTable symbolTable) throws TypeException
    {
        checkError();
        
        return null;
    }
}
