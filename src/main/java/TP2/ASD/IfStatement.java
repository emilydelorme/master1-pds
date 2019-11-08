package TP2.ASD;

import TP2.exceptions.TypeException;

import java.util.List;

public class IfStatement implements Statement {

    private Expression expression;
    private List<Statement> block;

    public IfStatement(Expression expression, List<Statement> block) {
        this.expression = expression;
        this.block = block;
    }

    @Override
    public String pp() {
        return "if(" + expression.pp() + ")\n" + block.stream()
                .map(Statement::pp)
                .reduce((statement, statement2) -> statement + "\n" + statement2);
    }

    @Override
    public RetInstruction toIR() throws TypeException {
        //TODO: IF to IR()
        return null;
    }
}
