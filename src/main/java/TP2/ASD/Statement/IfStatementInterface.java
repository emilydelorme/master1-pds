package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

import java.util.List;

public class IfStatementInterface implements StatementInterface {

    private ExpressionInterface expressionInterface;
    private List<StatementInterface> block;

    public IfStatementInterface(ExpressionInterface expressionInterface, List<StatementInterface> block) {
        this.expressionInterface = expressionInterface;
        this.block = block;
    }

    @Override
    public String pp() {
        return "if(" + expressionInterface.pp() + ")\n" + block.stream()
                .map(StatementInterface::pp)
                .reduce((statement, statement2) -> statement + "\n" + statement2);
    }

    @Override
    public RetInstruction toIR() throws TypeException {
        //TODO: IF to IR()
        return null;
    }
}
