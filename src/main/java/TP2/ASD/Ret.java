package TP2.ASD;

import TP2.LlvmOld;

public class Ret
{
    public LlvmOld.IR ir;
    // And additional stuff:
    public TypeInterface type; // The type of the expression
    public String result; // The name containing the expression's result
    // (either an identifier, or an immediate value)

    public Ret(LlvmOld.IR ir, TypeInterface type, String result)
    {
        this.ir = ir;
        this.type = type;
        this.result = result;
    }
}
