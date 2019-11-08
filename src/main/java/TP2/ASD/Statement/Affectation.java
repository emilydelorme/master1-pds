package TP2.ASD.Statement;

import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

// Concrete class for Expression: add case
public class Affectation implements StatementInterface {
    private String ident;
    private StatementInterface statementInterface;

    public Affectation(String ident, StatementInterface statementInterface) {
      this.ident = ident;
      this.statementInterface = statementInterface;
    }

    // Pretty-printer
    public String pp()
    {
        return this.ident + " = " + statementInterface.pp();
    }

    @Override
    public Ret toIR() throws TypeException {

        return null;
    }
}