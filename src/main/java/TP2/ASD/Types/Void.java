package TP2.ASD.Types;

import TP2.ASD.TypeInterface;
import TP2.LlvmOld;

public class Void implements TypeInterface
{

    @Override
    public String pp()
    {
        return "VOID";
    }

    @Override
    public LlvmOld.Type toLlvmType()
    {
        return new LlvmOld.Void();
    }
}
