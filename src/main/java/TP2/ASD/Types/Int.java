package TP2.ASD.Types;

import TP2.ASD.TypeInterface;
import TP2.Llvm.Type;
import TP2.Llvm.Types.LlvmInt;

public class Int implements TypeInterface
{
    public String pp()
    {
        return "INT";
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Int;
    }

    public Type toLlvmType()
    {
        return new LlvmInt();
    }
}