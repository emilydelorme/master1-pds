package TP2.ASD;

// Concrete class for Expression: add case
public class Affectation
{
    private String ident;
    private Expression right;

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