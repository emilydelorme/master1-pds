package TP2.ASD;

import TP2.exceptions.TypeException;

public class Declaration implements Statement {
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
    public RetInstruction toIR() throws TypeException {
        return null;
    }


}
