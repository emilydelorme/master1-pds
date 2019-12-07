package TP2.Llvm.Instructions.load;

import TP2.Llvm.Instruction;

public class LoadVar implements Instruction
{
    String ident;
    String toLoadIdent;

    public LoadVar(String ident, String toLoadIdent) {
        this.ident = ident;
        this.toLoadIdent = toLoadIdent;
    }

    public String toString() {
        return ident + " = load i32, i32* " + toLoadIdent + "\n";
    }
}
