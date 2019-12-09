package TP2.SymbolTable;

import TP2.ASD.TypeInterface;

import java.util.List;
import java.util.Objects;

public class PrototypeSymbol extends Symbol
{
    private TypeInterface returnType;
    private List<VariableSymbol> arguments;
    private boolean defined;

    public PrototypeSymbol(TypeInterface returnType, String ident, List<VariableSymbol> arguments, boolean defined)
    {
        super(ident);

        this.returnType = returnType;
        this.arguments = arguments;
        this.defined = defined;
    }

    public TypeInterface getReturnType()
    {
        return returnType;
    }

    public List<VariableSymbol> getArguments()
    {
        return arguments;
    }

    public boolean isDefined()
    {
        return defined;
    }

    public void setDefined(boolean defined)
    {
        this.defined = defined;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PrototypeSymbol)) return false;
        if (!super.equals(o)) return false;
        PrototypeSymbol that = (PrototypeSymbol) o;
        return isDefined() == that.isDefined() &&
                Objects.equals(getReturnType(), that.getReturnType()) &&
                Objects.equals(getArguments(), that.getArguments());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), getReturnType(), getArguments(), isDefined());
    }

    @Override
    public String toString() {
        return "PrototypeSymbol{\n" +
               "returnType=" + returnType +
               ", arguments=" + arguments +
               ", defined=" + defined +
               "}\n";
    }
}
