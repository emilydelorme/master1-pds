package TP2.SymbolTable;

import java.util.Objects;
import TP2.ASD.TypeInterface;

public class VariableSymbol extends Symbol
{
    private TypeInterface type;

    public VariableSymbol(TypeInterface type, String ident)
    {
        super(ident);

        this.type = type;
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
        
        if (!super.equals(obj))
        {
            return false;
        }

        if (!(obj instanceof VariableSymbol))
        {
            return false;
        }
        
        final VariableSymbol other = (VariableSymbol) obj;

        if (!Objects.equals(this.type, other.type))
        {
            return false;
        }
        
        return true;
    }
}
