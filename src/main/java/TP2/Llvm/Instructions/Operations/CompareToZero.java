package TP2.Llvm.Instructions.Operations;

import TP2.Llvm.Instruction;

public class CompareToZero implements Instruction {
    private final String ident;
    private final String valueToCompare;

    public CompareToZero(String ident, String valueToCompare) {
        this.ident = ident;
        this.valueToCompare = valueToCompare;
    }

    public String toString() {
        return ident + " = icmp ne i32 " + valueToCompare + ", 0\n";
    }
}

