package TP2.Llvm.Instructions.functions;

import TP2.Llvm.Instruction;
import TP2.Llvm.Type;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefineFunction implements Instruction {

    private final Type type;
    private final String ident;
    private final List<String> parameters;

    public DefineFunction(Type type, String name,List<String> params) {
        this.type = type;
        this.ident = name;
        this.parameters = params;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("define " + type.toString() + " @" + ident + "(");
        if(!parameters.isEmpty()) {
            res.append("i32 %").append(parameters.get(0));
            for (int i = 1; i < parameters.size(); i++) {
                res.append(", " + "i32 %").append(parameters.get(i));
            }
        }
        return  res + "){\n";
    }
}
