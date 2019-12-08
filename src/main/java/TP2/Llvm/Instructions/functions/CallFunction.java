package TP2.Llvm.Instructions.functions;

import TP2.Llvm.Instruction;
import TP2.Llvm.LlvmUtils;
import TP2.Llvm.Type;

import java.util.List;
import java.util.stream.IntStream;

public class CallFunction implements Instruction {

    private final Type type;
    private final String funcName;
    private final List<String> variables;

    public CallFunction(Type type, String funcName, List<String> variables) {
        this.type = type;
        this.funcName = funcName;
        this.variables = variables;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(LlvmUtils.IDENT + "call void  @" + funcName + "(");
        IntStream.range(0, variables.size()).forEach(i -> {
            res.append("i32 ").append(variables.get(i));
            if (i < variables.size() - 1) {
                res.append(", ");
            }
        });
        return res + ")\n";
    }
}
