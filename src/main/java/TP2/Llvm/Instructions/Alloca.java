package TP2.Llvm.Instructions;


import TP2.Llvm.Instruction;
import TP2.Llvm.Type;

public class Alloca implements Instruction {
    private Type type;
    private String value;

    public Alloca(Type type, String lvalue)
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
