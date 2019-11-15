package TP2.Llvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ir {

    private final List<Instruction> header; // IR instructions to be placed before the code (global definitions)
    private final List<Instruction> code; // main code
    
    public Ir() {
        this.header = new ArrayList<>();
        this.code = new ArrayList<>();
    }

    public Ir(List<Instruction> code) {
        this.header = new ArrayList<>();
        this.code = code;
    }

    public Ir(List<Instruction> header, List<Instruction> code)
    {
        this.header = header;
        this.code = code;
    }

    // Final string generation
    public String toString()
    {
        StringBuilder r = new StringBuilder();
        code.forEach(r::append);
        return r.toString();
    }
}
