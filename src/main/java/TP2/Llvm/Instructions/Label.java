package TP2.Llvm.Instructions;

import TP2.Llvm.Instruction;

public class Label implements Instruction
{
    String label;

    public Label(String label) {
        this.label = label;
    }

    public String toString() {
        return label + ":\n";
    }
}
