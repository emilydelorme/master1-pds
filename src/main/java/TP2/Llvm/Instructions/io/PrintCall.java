package TP2.Llvm.Instructions.io;

import TP2.Llvm.Instruction;

import java.util.List;
import java.util.stream.Collectors;

public class PrintCall implements Instruction
{
    String printIdent;
    int textSize;
    List<String> variables;

    public PrintCall(String printIdent, int textSize, List<String> variables) {
        this.printIdent = printIdent;
        this.textSize = textSize;
        this.variables = variables;
    }

    public String toString() {
        return variables.stream()
                .map(var -> ", i32 " + var)
                .collect(Collectors.joining("", "call i32 (i8* , ...) @printf(i8* getelementptr inbounds ([" + textSize + " x i8], ["
                        + textSize + " x i8]* @." + printIdent + " , i64 0 , i64 0)", ")\n"));
    }
}
