package TP2.Llvm.Instructions;

import TP2.Llvm.Instruction;
import TP2.Llvm.LlvmUtils;
import TP2.Llvm.Type;

public class Store implements Instruction {
    private final Type type;
    private final String ident;
    private final String pointer;

    public Store(Type type, String ident, String pointer)
    {
        this.type = type;
        this.ident = ident;
        this.pointer = pointer;
    }

    @Override
    public String toString()
    {
        return LlvmUtils.IDENT + "store " + type + " %" + ident + ", " + type + "* %" + pointer + "\n";
    }
}
