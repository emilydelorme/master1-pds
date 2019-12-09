package TP2.Llvm.Instructions;

import TP2.Llvm.Instruction;
import TP2.Llvm.LlvmUtils;
import TP2.Llvm.Type;
import TP2.Llvm.Types.LlvmInt;

public class Return implements Instruction {
    private Type type;
    private String value;

    public Return(Type type) {
        this.type = type;
    }

    public Return(Type type, String value)
    {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString()
    {
        if(value == null) {
            if(type instanceof LlvmInt) {
                return LlvmUtils.IDENT + "ret i32 0\n";
            } else {
                return LlvmUtils.IDENT + "ret void\n";
            }
        } else {
            return LlvmUtils.IDENT + "ret " + type + " " + value + "\n";
        }
    }
}
