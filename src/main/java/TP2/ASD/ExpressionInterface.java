package TP2.ASD;

import TP2.Llvm;
import TP2.exceptions.TypeException;

public interface ExpressionInterface {
    String pp();
    ExpressionInterface.RetExpression toIR() throws TypeException;

    // Object returned by toIR on expressions, with IR + synthesized attributes
    class RetExpression {
      // The LLVM IR:
      public Llvm.IR ir;
      // And additional stuff:
      public TypeInterface type; // The type of the expression
        public String result; // The name containing the expression's result
      // (either an identifier, or an immediate value)

      public RetExpression(Llvm.IR ir, TypeInterface type, String result) {
        this.ir = ir;
        this.type = type;
        this.result = result;
      }
    }
  }