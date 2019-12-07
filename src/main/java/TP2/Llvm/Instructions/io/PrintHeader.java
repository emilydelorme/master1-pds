package TP2.Llvm.Instructions.io;

import TP2.Llvm.Instruction;
import TP2.Utils;

public class PrintHeader implements Instruction
{
    String printIdent;
    Utils.LLVMStringConstant text;

    public PrintHeader(String printIdent, Utils.LLVMStringConstant text)
    {
        this.printIdent = printIdent;
        this.text = text;
    }

    public String toString()
    {
        return "@." + printIdent + " = global [" + text.getLength() + " x i8] c\"" + text.getStr() + "\"\n";
    }

    public int getLenght() {
        return text.getLength();
    }
}
