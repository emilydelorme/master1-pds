package TP2.ASD.Types;

import TP2.ASD.TypeInterface;
import TP2.Llvm.Type;
import TP2.Llvm.Types.LlvmVoid;

public class Void implements TypeInterface
{

    @Override
    public String pp()
    {
        return "VOID";
    }

    @Override
    public Type toLlvmType()
    {
        return new LlvmVoid();
    }
}
