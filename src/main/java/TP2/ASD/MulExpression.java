package TP2.ASD;

import TP2.Llvm;
import TP2.exceptions.TypeException;
import TP2.Utils;

// Concrete class for Expression: mul case
  public class MulExpression implements Expression {
    private Expression left;
    private Expression right;

    public MulExpression(Expression left, Expression right) {
      this.left = left;
      this.right = right;
    }

    // Pretty-printer
    public String pp() {
      return "(" + left.pp() + " * " + right.pp() + ")";
    }

    // IR generation (IR = Représentation intermédiaire)
    public RetExpression toIR() throws TypeException {
      RetExpression leftRet = left.toIR();
      RetExpression rightRet = right.toIR();

      // We check if the types mismatches
      if(!leftRet.type.equals(rightRet.type)) {
        throw new TypeException("type mismatch: have " + leftRet.type + " and " + rightRet.type);
      }

      // We base our build on the left generated IR:
      // append right code
      leftRet.ir.append(rightRet.ir);

      // allocate a new identifier for the result
      String result = Utils.newtmp();

      // new mul instruction result = left * right
      Llvm.Instruction mul = new Llvm.Mul(leftRet.type.toLlvmType(), leftRet.result, rightRet.result, result);

      // append this instruction
      leftRet.ir.appendCode(mul);

      // return the generated IR, plus the type of this expression
      // and where to find its result
      return new RetExpression(leftRet.ir, leftRet.type, result);
    }
  }