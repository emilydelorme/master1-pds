package TP2.ASD;

import TP2.Llvm;
import TP2.exceptions.TypeException;

import java.util.List;

public class WhileStatement implements Statement {

    private Expression expression;
    private List<Statement> block;

    public WhileStatement(Expression expression, List<Statement> block) {
        this.expression = expression;
        this.block = block;
    }

    @Override
    public String pp() {
        return "while("+expression.pp()+")\n"+block.stream()
                .map(Statement::pp)
                .reduce((statement1, statement2) -> statement1 + "\n" + statement2);
    }

    @Override
    public RetInstruction toIR() throws TypeException {
        //TODO: While to IR()
        return null;
    }
}
