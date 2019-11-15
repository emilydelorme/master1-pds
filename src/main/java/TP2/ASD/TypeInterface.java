package TP2.ASD;

import TP2.LlvmOld;

// Warning: this is the type from VSL+, not the LLVM types!
public interface TypeInterface
{
    String pp();
    LlvmOld.Type toLlvmType();
}