package TP2.Llvm.Instructions.alloca;

import TP2.Llvm.Instruction;
import TP2.Llvm.LlvmUtils;
import TP2.Llvm.Type;

public class AllocaTab implements Instruction {
    private final Type type;
    private final String ident;
    private final int size;

    public AllocaTab(Type type, String ident, int size) {
        this.type = type;
        this.ident = ident;
        this.size = size;
    }

    @Override
    public String toString() {
        return LlvmUtils.IDENT + "%" + ident + " = alloca [" + size + " x " + type + "]" + "\n";
    }
}
