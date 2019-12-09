package TP2.SymbolTable;

import TP2.ASD.TypeInterface;

import java.util.List;
import java.util.Objects;

public class FunctionSymbol extends Symbol
{
    private TypeInterface returnType;
    private List<VariableSymbol> arguments;
    //private boolean isPrototype;
    //private boolean isDefined;

    public FunctionSymbol(TypeInterface returnType, String ident, List<VariableSymbol> arguments/*, boolean isPrototype, boolean isDefined*/)
    {
        super(ident);

        this.returnType = returnType;
        this.arguments = arguments;
        //this.isPrototype = isPrototype;
        //this.isDefined = isDefined;
    }

    public TypeInterface getReturnType()
    {
        return returnType;
    }

    public List<VariableSymbol> getArguments()
    {
        return arguments;
    }
    
    /*
    public boolean isPrototype()
    {
        return isPrototype;
    }

    public boolean isDefined()
    {
        return isDefined;
    }
     */

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof FunctionSymbol)) return false;
        if (!super.equals(o)) return false;
        FunctionSymbol that = (FunctionSymbol) o;
        return Objects.equals(getReturnType(), that.getReturnType()) &&
                Objects.equals(getArguments(), that.getArguments());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), getReturnType(), getArguments());
    }
}
