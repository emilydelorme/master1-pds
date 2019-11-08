package TP2.ASD;

import TP2.Llvm;
import TP2.exceptions.TypeException;

public interface StatementInterface {
    String pp();
    RetInstruction toIR() throws TypeException;

    // Object returned by toIR on expressions, with IR + synthesized attributes
    class RetInstruction {
        // The LLVM IR:
        public Llvm.IR ir;
        // And additional stuff:
        public Type type; // The type of the expression
        String result; // The name containing the expression's result
        // (either an identifier, or an immediate value)

        public RetInstruction(Llvm.IR ir, Type type, String result) {
            this.ir = ir;
            this.type = type;
            this.result = result;
        }
    }

}
