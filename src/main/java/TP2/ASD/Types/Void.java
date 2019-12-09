package TP2.ASD.Types;

import TP2.ASD.TypeInterface;
import TP2.Llvm.Type;
import TP2.Llvm.Types.LlvmVoid;

import java.util.Objects;

public class Void implements TypeInterface
{
    private static final String void_t = "VOID";
    
    public String getType()
    {
        return void_t;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Void;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash();
    }
    
    @Override
    public String pp()
    {
        return void_t;
    }

    @Override
    public Type toLlvmType()
    {
        return new LlvmVoid();
    }
}
