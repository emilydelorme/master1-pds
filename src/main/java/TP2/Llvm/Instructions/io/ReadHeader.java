package TP2.Llvm.Instructions.io;

import TP2.Llvm.Instruction;
import TP2.Utils;

public class ReadHeader implements Instruction
{
    private final String readIdent;

    public ReadHeader(String readIdent)
    {
        this.readIdent = readIdent;
    }

    public String toString()
    {
        Utils.LLVMStringConstant modifiedString = Utils.stringTransform("%d");
        return "@." + readIdent + " = global [" + modifiedString.getLength() + " x i8] c\"" + modifiedString.getStr() + "\"\n";
    }
}
