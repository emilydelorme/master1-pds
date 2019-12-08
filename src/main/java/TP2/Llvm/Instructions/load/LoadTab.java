package TP2.Llvm.Instructions.load;

import TP2.Llvm.Instruction;
import TP2.Llvm.LlvmUtils;

public class LoadTab implements Instruction
{
    private final String ident;
    private final String tabIdent;
    private final String tabIndex;
    private final String tabSize;

    public LoadTab(String ident, String tabIdent, String tabIndex, String tabSize) {
        this.ident = ident;
        this.tabIdent = tabIdent;
        this.tabIndex = tabIndex;
        this.tabSize = tabSize;
    }

    public String toString() {
        return LlvmUtils.IDENT + ident + " = getelementptr [" + tabSize + " x i32], [" + tabSize + " x i32]* " + tabIdent
               + ", i64 0, i32 " + tabIndex + "\n";
    }
}
