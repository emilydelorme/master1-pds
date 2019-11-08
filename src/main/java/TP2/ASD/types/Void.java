package TP2.ASD.types;

import TP2.ASD.Type;
import TP2.Llvm;

public class Void implements Type {

    @Override
    public String pp() {
        return "VOID";
    }

    @Override
    public Llvm.Type toLlvmType() {
        return new Llvm.Void();
    }
}
