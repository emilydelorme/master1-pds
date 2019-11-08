package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class IfElseStatement implements StatementInterface {

    private ExpressionInterface expression;
    private Block trueBlock, falseBlock;

    public IfElseStatement(ExpressionInterface expression, Block trueBlock, Block falseBlock) {
        this.expression = expression;
        this.trueBlock = trueBlock;
        this.falseBlock = falseBlock;
    }

    @Override
    public String pp() {
        return "if(" + expression.pp() + ")\n" + trueBlock.pp() + "else\n" + falseBlock.pp();
    }

    @Override
    public RetInstruction toIR() throws TypeException {
        return null;
    }
}
