package TP2.Llvm.Instructions.functions;

import TP2.Llvm.Instruction;

public class ProtoFunction implements Instruction {

    private final String ident;

    public ProtoFunction(String ident) {
        this.ident = ident;
    }

    public String getIdent() {
        return ident;
    }

    @Override
    public String toString() {
        return ";PROTO " + ident + " - Function not implemented\n";
    }
}
