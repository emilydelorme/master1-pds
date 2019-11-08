package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

import java.util.List;

public class WhileStatementInterface implements StatementInterface {

    private ExpressionInterface expressionInterface;
    private List<StatementInterface> block;

    public WhileStatementInterface(ExpressionInterface expressionInterface, List<StatementInterface> block) {
        this.expressionInterface = expressionInterface;
        this.block = block;
    }

    @Override
    public String pp() {
        return "while("+ expressionInterface.pp()+")\n"+block.stream()
                .map(StatementInterface::pp)
                .reduce((statement1, statement2) -> statement1 + "\n" + statement2);
    }

    @Override
    public RetInstruction toIR() throws TypeException {
        //TODO: While to IR()
        return null;
    }
}
