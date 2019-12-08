package TP2.Llvm.Instructions;

import TP2.Llvm.Instruction;
import TP2.Llvm.LlvmUtils;

import java.util.Objects;

public class Br implements Instruction
{
    private final String mainLabel;
    private final String condition;
    private final String altLabel;

    public Br(String condition, String mainLabel, String altLabel) {
        this.condition = condition;
        this.mainLabel = mainLabel;
        this.altLabel = altLabel;
    }

    public Br(String mainLabel)
    {
        this.mainLabel = mainLabel;
        condition = null;
        altLabel = null;
    }

    public String toString() {
        return Objects.isNull(condition)?
               LlvmUtils.IDENT +"br label %" + mainLabel + "\n\n" :
               LlvmUtils.IDENT + "br i1 " + condition + ", label %" + mainLabel + ", label %" + altLabel + "\n\n";
    }
}
