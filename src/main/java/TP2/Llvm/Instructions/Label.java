package TP2.Llvm.Instructions;

import TP2.Llvm.Instruction;

public class Label implements Instruction
{
    private final String llvmLabel;

    public Label(String llvmLabel) {
        this.llvmLabel = llvmLabel;
    }

    public String toString() {
        return llvmLabel + ":\n";
    }
}
