package TP2.SymbolTable;

import java.util.Objects;

public abstract class Symbol
{
    private String ident;

    public Symbol(String ident)
    {
        this.ident = ident;
    }

    public String getIdent()
    {
        return this.ident;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(this.ident);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (!(obj instanceof Symbol))
        {
            return false;
        }
        
        final Symbol other = (Symbol) obj;

        if (!Objects.equals(this.ident, other.ident))
        {
            return false;
        }
        
        return true;
    }

}