package TP2.ASD.types;

import TP2.ASD.Type;
import TP2.Llvm;

public class Int implements Type {
    public String pp() {
      return "INT";
    }

    @Override public boolean equals(Object obj) {
      return obj instanceof Int;
    }

    public Llvm.Type toLlvmType() {
      return new Llvm.Int();
    }
  }