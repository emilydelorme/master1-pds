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
        this.llvmIdent = ident;
    }
    
    public VariableSymbol(TypeInterface type, String ident, boolean isArray, int size)
    {
        super(ident);

        this.type = type;
        this.isArray = isArray;
        this.size = size;
        this.llvmIdent = ident;
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VariableSymbol that = (VariableSymbol) o;

        return Objects.equals(this.getIdent(), that.getIdent()) &&
                Objects.equals(this.isArray, that.isArray) &&
                Objects.equals(this.size, that.size) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.getIdent(), isArray, size, type);
    }

    @Override
    public String toString() {
        return "VariableSymbol{" +
               "type=" + type +
               ", isArray=" + isArray +
               ", size=" + size +
               ", llvmIdent='" + llvmIdent + '\'' +
               "}\n";
    }
}
