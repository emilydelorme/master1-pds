package TP2.ASD.Ret;

import TP2.ASD.TypeInterface;
import TP2.ASD.Types.Void;
import TP2.Llvm.InstructionHandler;

public class TypeRet extends GenericRet
{

    private TypeInterface type;  // The type of the expression

    public TypeRet(InstructionHandler ir, String result, TypeInterface type)
    {
        super(ir, result);
        this.type = type;
    }

    public TypeRet(GenericRet genericRet, TypeInterface type)
    {
        super(genericRet.getIr(), genericRet.getResult());
        this.type = type;
    }

    public TypeInterface getType()
    {
        return type;
    }

    public void setType(TypeInterface type)
    {
        this.type = type;
    }
}
