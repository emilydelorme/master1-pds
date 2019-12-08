package TP2.Llvm.Instructions;

import TP2.Llvm.Instruction;
import TP2.Llvm.LlvmUtils;
import TP2.Llvm.Type;

public class Return implements Instruction {
    private Type type;
    private String value;

    public Return(Type type, String value)
    {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return LlvmUtils.IDENT + "ret " + type + " " + value + "\n";
    }
}
