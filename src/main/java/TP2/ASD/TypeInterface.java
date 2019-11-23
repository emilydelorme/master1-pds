package TP2.ASD;

import TP2.Llvm.Type;

// Warning: this is the type from VSL+, not the LLVM types!
public interface TypeInterface
{
    String pp();
    Type toLlvmType();
}