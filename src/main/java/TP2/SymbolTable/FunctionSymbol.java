package TP2.SymbolTable;

import java.util.List;
import java.util.Objects;

import TP2.ASD.TypeInterface;

public class FunctionSymbol extends Symbol
{
    private TypeInterface returnType;
    private List<VariableSymbol> arguments;

    public FunctionSymbol(TypeInterface returnType, String ident, List<VariableSymbol> arguments)
    {
        super(ident);

        this.returnType = returnType;
        this.arguments = arguments;
    }

    public TypeInterface getReturnType()
    {
        return returnType;
    }

    public List<VariableSymbol> getArguments()
    {
        return arguments;
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

        if (!(obj instanceof FunctionSymbol))
        {
            return false;
        }
        
        final FunctionSymbol other = (FunctionSymbol) obj;


        if (!Objects.equals(this.returnType, other.returnType))
        {
            return false;
        }

        if (!Objects.equals(this.arguments, other.arguments))
        {
            return false;
        }
        
        return true;
    }
}
