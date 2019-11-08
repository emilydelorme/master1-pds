package TP2.ASD;

public class Declaration
{
    private String ident;

    public Declaration(String ident) {
        this.ident = ident;
    }
    // Pretty-printer
    public String pp()
    {
        return this.ident;
    }
    
}
