package TP2.Llvm.Instructions;

import TP2.LlvmOld.Instruction;
import TP2.LlvmOld;

public class Alloca implements Instruction {
    private LlvmOld.Type type;
    private String value;

    public Alloca(LlvmOld.Type type, String lvalue)
    {
        this.type = type;
        this.value = lvalue;
    }

    @Override
    public String toString()
    {
        return value + " = alloca " + type;
    }
}
