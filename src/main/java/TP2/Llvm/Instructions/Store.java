package TP2.Llvm.Instructions;

import TP2.Llvm.Instruction;
import TP2.Llvm.Type;

public class Store implements Instruction {
    private Type type;
    private String value;
    private String pointer;

    public Store(Type type, String value, String pointer)
    {
        this.type = type;
        this.value = value;
        this.pointer = pointer;
    }

    @Override
    public String toString()
    {
        return "store " + type + " " + value + ", " + type + "* " + pointer;
    }
}
