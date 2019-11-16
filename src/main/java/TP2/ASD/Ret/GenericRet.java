package TP2.ASD;


import TP2.Llvm.InstructionHandler;

public class Ret
{
    public InstructionHandler ir;
    // And additional stuff:
    public TypeInterface type; // The type of the expression
    public String result; // The name containing the expression's result
    // (either an identifier, or an immediate value)

    public Ret(InstructionHandler ir, TypeInterface type, String result)
    {
        this.ir = ir;
        this.type = type;
        this.result = result;
    }
}
