package TP2.ASD.Statement;

import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class Declaration implements StatementInterface {
    private String ident;

    public Declaration(String ident) {
        this.ident = ident;
    }
    // Pretty-printer
    public String pp()
    {
        return this.ident;
    }

    @Override
    public Ret toIR() throws TypeException {
        return null;
    }


}
