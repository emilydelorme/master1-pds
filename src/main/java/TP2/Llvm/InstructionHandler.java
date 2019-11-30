package TP2.Llvm;

import java.util.ArrayList;
import java.util.List;

public class InstructionHandler
{

    private final List<Instruction> header; // IR instructions to be placed before the code (global definitions)
    private final List<Instruction> code; // main code

    public InstructionHandler()
    {
        this.header = new ArrayList<>();
        this.code = new ArrayList<>();
    }

    public InstructionHandler(List<Instruction> code)
    {
        this.header = new ArrayList<>();
        this.code = code;
    }

    public InstructionHandler(List<Instruction> header, List<Instruction> code)
    {
        this.header = header;
        this.code = code;
    }

    public InstructionHandler(InstructionHandler ir)
    {
        this();
        this.appendAll(ir);
    }

    public void appendHeader(Instruction instruction)
    {
        this.code.add(instruction);
    }

    public void appendCode(Instruction instruction)
    {
        this.code.add(instruction);
    }

    public void appendAll(List<Instruction> header, List<Instruction> code)
    {
        this.header.addAll(header);
        this.code.addAll(code);
    }

    public void appendCode(List<Instruction> code)
    {
        this.code.addAll(code);
    }

    public void appendHeader(List<Instruction> header)
    {
        this.header.addAll(header);
    }

    public void appendAll(InstructionHandler ir)
    {
        this.header.addAll(ir.header);
        this.code.addAll(ir.code);
    }

    // Final string generation
    @Override
    public String toString()
    {
        StringBuilder r = new StringBuilder();
        header.forEach(r::append);
        code.forEach(r::append);
        return r.toString();
    }

}
