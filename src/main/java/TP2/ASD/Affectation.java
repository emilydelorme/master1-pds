package TP2.ASD;

import TP2.Llvm;
import TP2.TypeException;
import TP2.Utils;

// Concrete class for Expression: add case
public class Affectation
{
    String ident;
    Expression right;

    public Affectation(String ident, Expression right) {
      this.ident = ident;
      this.right = right;
    }

    // Pretty-printer
    public String pp()
    {
        return this.ident + " = " + right.pp();
    }
}