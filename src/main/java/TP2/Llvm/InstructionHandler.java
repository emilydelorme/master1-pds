package TP2.Llvm;

import TP2.Llvm.Instructions.functions.ProtoFunction;

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

    public InstructionHandler appendHeader(Instruction instruction)
    {
        this.header.add(instruction);
        return this;
    }

    public InstructionHandler appendCode(Instruction instruction)
    {
        this.code.add(instruction);
        return this;
    }

    public InstructionHandler appendAll(List<Instruction> header, List<Instruction> code)
    {
        this.header.addAll(header);
        this.code.addAll(code);
        return this;
    }

    public InstructionHandler appendCode(List<Instruction> code)
    {
        this.code.addAll(code);
        return this;
    }

    public InstructionHandler appendHeader(List<Instruction> header)
    {
        this.header.addAll(header);
        return this;
    }

    public InstructionHandler appendAll(InstructionHandler ir)
    {
        this.header.addAll(ir.header);
        this.code.addAll(ir.code);
        return this;
    }

    public Instruction getProto(String ident) {
        for(Instruction instruction : code) {
            if(instruction instanceof ProtoFunction) {
                ProtoFunction proto = (ProtoFunction) instruction;
                if(proto.getIdent().equals(ident)) {
                    return proto;
                }
            }
        }
        return null;
    }

    public void replaceProto(String ident, InstructionHandler ir) {
        Instruction protoInstruction = getProto(ident);

        int startIndex = this.code.indexOf(protoInstruction);
        this.code.addAll(startIndex,ir.code);
        this.code.remove(protoInstruction);

    }

    public boolean isEmpty() {
        return this.code.isEmpty() && this.header.isEmpty();
    }

    // Final string generation
    @Override
    public String toString()
    {
        StringBuilder r = new StringBuilder();
        r.append(LlvmUtils.globalInit())
         .append("\n");

        if(!header.isEmpty())
            r.append("; Headers\n");
        header.forEach(r::append);
        if(!header.isEmpty())
            r.append("\n\n");

        r.append("; Code\n");
        code.forEach(r::append);
        return r.toString();
    }

}
