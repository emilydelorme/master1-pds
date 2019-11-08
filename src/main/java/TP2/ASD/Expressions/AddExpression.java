package TP2.ASD.Expressions;

import TP2.ASD.Expression;
import TP2.Llvm;
import TP2.exceptions.TypeException;
import TP2.Utils;

// Concrete class for Expression: add case
  public class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
      this.left = left;
      this.right = right;
    }

    // Pretty-printer
    public String pp() {
      return "(" + left.pp() + " + " + right.pp() + ")";
    }

    // IR generation (IR = Représentation intermédiaire)
    public RetExpression toIR() throws TypeException {
      return ExpressionHelper.retExpression(left.toIR(), right.toIR());
    }
  }