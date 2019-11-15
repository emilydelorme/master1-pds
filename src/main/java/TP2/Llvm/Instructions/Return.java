package TP2.Llvm.Instructions;

import TP2.LlvmOld.Instruction;
import TP2.LlvmOld;

public class Return implements Instruction {
    private LlvmOld.Type type;
    private String value;

    public Return(LlvmOld.Type type, String value)
    {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "ret " + type + " " + value + "\n";
    }
}
