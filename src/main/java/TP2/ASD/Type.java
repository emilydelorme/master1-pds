package TP2.ASD;

import TP2.Llvm;

// Warning: this is the type from VSL+, not the LLVM types!
  public interface Type {
    String pp();
    Llvm.Type toLlvmType();
  }