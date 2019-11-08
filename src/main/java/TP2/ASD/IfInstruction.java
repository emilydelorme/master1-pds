package TP2.ASD;

import TP2.exceptions.TypeException;

import java.util.List;

public class IfInstruction implements Instruction {

    private Expression expression;
    private List<Instruction> block;

    public IfInstruction(Expression expression, List<Instruction> block) {
        this.expression = expression;
        this.block = block;
    }

    @Override
    public String pp() {
        return "if(" + expression.pp() + ")\n" + block.stream()
                .map(Instruction::pp)
                .reduce((statement, statement2) -> statement + "\n" + statement2);
    }

    @Override
    public RetInstruction toIR() throws TypeException {
        //TODO: IF to IR()
        return null;
    }
}
