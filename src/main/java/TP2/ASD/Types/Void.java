package TP2.ASD.Types;

import TP2.ASD.TypeInterface;
import TP2.Llvm;

public class Void implements TypeInterface
{

    @Override
    public String pp()
    {
        return "VOID";
    }

    @Override
    public Llvm.Type toLlvmType()
    {
        return new Llvm.Void();
    }
}
