package TP2.Llvm.Instructions.load;

import TP2.Llvm.Instruction;
import TP2.Llvm.LlvmUtils;

public class LoadVar implements Instruction
{
    private final String ident;
    private final String toLoadIdent;

    public LoadVar(String ident, String toLoadIdent) {
        this.ident = ident;
        this.toLoadIdent = toLoadIdent;
    }

    public String toString() {
        return LlvmUtils.IDENT +  ident + " = load i32, i32* " + toLoadIdent + "\n";
    }
}
