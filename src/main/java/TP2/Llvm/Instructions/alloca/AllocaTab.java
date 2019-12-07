package TP2.Llvm.Instructions.alloca;

import TP2.Llvm.Instruction;
import TP2.Llvm.Type;

public class AllocaTab implements Instruction
{
    final private Type type;
    final private String ident;
    final private int size;

    public AllocaTab(Type type, String ident, int size)
    {
        this.type = type;
        this.ident = ident;
        this.size = size;
    }

    @Override
    public String toString()
    {
        return ident + " = alloca [" + size + " x " + type + "]";
    }
}
