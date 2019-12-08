package TP2.Llvm.Instructions.Operations;

import TP2.Llvm.Instruction;
import TP2.Llvm.LlvmUtils;

public class GenericOperation implements Instruction {
    private final Operation operation;
    private final String left;
    private final String right;
    private final String lvalue;

    public GenericOperation(Operation operation, String left, String right, String lvalue) {
        this.operation = operation;
        this.left = left;
        this.right = right;
        this.lvalue = lvalue;
    }

    @Override
    public String toString() {
        return LlvmUtils.IDENT + lvalue + " = add " + operation.toString().toLowerCase() + " " + left + ", " + right + "\n";
    }
}
