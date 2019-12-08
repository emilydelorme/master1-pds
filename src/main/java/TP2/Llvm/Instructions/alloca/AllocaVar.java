package TP2.Llvm.Instructions.alloca;


import TP2.Llvm.Instruction;
import TP2.Llvm.Type;

public class AllocaVar implements Instruction {
    private final Type type;
    private final String value;

    public AllocaVar(Type type, String lvalue) {
        this.type = type;
        this.value = lvalue;
    }

    @Override
    public String toString() {
        return value + " = alloca " + type;
    }
}
