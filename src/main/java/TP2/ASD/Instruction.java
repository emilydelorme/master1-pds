package TP2.ASD;

import TP2.Llvm;
import TP2.exceptions.TypeException;

public interface Instruction {
    String pp();
    RetIntruction toIR() throws TypeException;

    // Object returned by toIR on expressions, with IR + synthesized attributes
    static class RetIntruction {
        // The LLVM IR:
        public Llvm.IR ir;
        // And additional stuff:
        public Type type; // The type of the expression
        String result; // The name containing the expression's result
        // (either an identifier, or an immediate value)

        public RetIntruction(Llvm.IR ir, Type type, String result) {
            this.ir = ir;
            this.type = type;
            this.result = result;
        }
    }

}
