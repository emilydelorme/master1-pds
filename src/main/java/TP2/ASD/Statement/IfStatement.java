package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class IfStatement implements StatementInterface {

    private ExpressionInterface expression;
    private Block block;

    public IfStatement(ExpressionInterface expression, Block block) {
        this.expression = expression;
        this.block = block;
    }

    @Override
    public String pp() {
        return "if(" + expression.pp() + ")\n" + block.pp();
    }

    @Override
    public RetInstruction toIR() throws TypeException {
        //TODO: IF to IR()
        return null;
    }
}
