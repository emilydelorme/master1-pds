package TP2.SymbolTable;

import TP2.ASD.TypeInterface;

import java.util.Objects;

public class VariableSymbol extends Symbol
{
    private TypeInterface type;
    //private byte state;
    private boolean isArray;
    private int size;
    private String llvmIdent;

    //public static final byte STATE_DECLARATION =    1;
    //public static final byte STATE_PARAMETER =      2;
    //public static final byte STATE_USAGE =          3;

    public VariableSymbol(TypeInterface type, String ident, boolean isArray)
    {
        super(ident);

        this.type = type;
        this.isArray = isArray;
    }
    
    public VariableSymbol(TypeInterface type, String ident, boolean isArray, int size)
    {
        super(ident);

        this.type = type;
        this.isArray = isArray;
        this.size = size;
    }

    public TypeInterface getType()
    {
        return type;
    }

    public boolean isArray()
    {
        return isArray;
    }

    public int getSize()
    {
        return this.size;
    }

    public String getLlvmIdent() {
        return llvmIdent;
    }

    public void setLlvmIdent(String llvmIdent) {
        this.llvmIdent = llvmIdent;
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
        
        if (this.isArray && other.isArray && this.size != other.size)
        {
            return false;
        }

        return Objects.equals(this.type, other.type);
    }
}
