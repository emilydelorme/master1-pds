package TP2.SymbolTable;

import java.util.Objects;

public abstract class Symbol
{
    private final String ident;

    public Symbol(String ident)
    {
        this.ident = ident;
    }

    public String getIdent()
    {
        return this.ident;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol that = (Symbol) o;

        return Objects.equals(this.ident, that.ident);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ident);
    }
}