package TP2.Llvm.Instructions.functions;

import TP2.Llvm.Instruction;

public class CloseFunction implements Instruction {

    public CloseFunction() {
    }

    @Override
    public String toString() {
        return "}\n";
    }
}
