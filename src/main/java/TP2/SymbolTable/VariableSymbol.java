package TP2.SymbolTable;

import java.util.Objects;
import TP2.ASD.TypeInterface;

public class VariableSymbol extends Symbol
{
    private TypeInterface type;
    //private byte state;
    private boolean isArray;
    
    //public static final byte STATE_DECLARATION =    1;
    //public static final byte STATE_PARAMETER =      2;
    //public static final byte STATE_USAGE =          3;

    public VariableSymbol(TypeInterface type, String ident, boolean isArray)
    {
        super(ident);

        this.type = type;
        this.isArray = isArray;
    }

    public TypeInterface getType()
    {
        return type;
    }

    public boolean isArray()
    {
        return isArray;
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

        if (this.isArray != other.isArray)
        {
            return false;
        }
        
        if (!Objects.equals(this.type, other.type))
        {
            return false;
        }
        
        return true;
    }
}
