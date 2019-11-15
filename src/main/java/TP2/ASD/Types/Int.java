package TP2.ASD.Types;

import TP2.ASD.TypeInterface;
import TP2.LlvmOld;

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

    public LlvmOld.Type toLlvmType()
    {
        return new LlvmOld.Int();
    }
}