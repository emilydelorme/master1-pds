package TP2.Llvm.Instructions.io;

import TP2.Llvm.Instruction;
import TP2.Utils;

public class ReadCall implements Instruction
{
    String readIdent;
    String nomVar;

    public ReadCall(String readIdent, String nomVar) {
        this.readIdent = readIdent;
        this.nomVar = nomVar;
    }

    public String toString() {
        Utils.LLVMStringConstant modifiedString = Utils.stringTransform("%d");
        return "call i32 (i8* , ...) @scanf(i8* getelementptr inbounds ([" + modifiedString.getLength()
                + " x i8], [" + modifiedString.getLength() + " x i8]* @." + readIdent + " , i64 0 , i64 0), i32* " + nomVar
                + ")\n";
    }

}