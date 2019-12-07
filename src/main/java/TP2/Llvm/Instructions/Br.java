package TP2.Llvm.Instructions;

import TP2.Llvm.Instruction;

public class Br implements Instruction
{
    String condition;
    String mainLabel;
    String AltLabel;

    public Br(String condition, String mainLabel, String AltLabel) {
        this.condition = condition;
        this.mainLabel = mainLabel;
        this.AltLabel = AltLabel;
    }

    public Br(String mainLabel)
    {
        this.mainLabel = mainLabel;
    }

    public String toString() {
        return condition != null ?
                "br i1 " + condition + ", label %" + mainLabel + ", label %" + AltLabel + "\n" :
                "br label %" + mainLabel + "\n";
    }
}
