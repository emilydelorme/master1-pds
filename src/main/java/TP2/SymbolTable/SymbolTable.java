package TP2.SymbolTable;

import java.util.HashMap;
import java.util.Objects;

import java.util.Map;

public class SymbolTable
{
    private Map<String, Symbol> table;
    private SymbolTable parent;

    public SymbolTable()
    {
        this.table = new HashMap<>();
        this.parent = null;
    }

    public void setParent(SymbolTable parent)
    {
        this.parent = parent;
    }

    public boolean add(Symbol symbol)
    {
        if (symbol == null)
        {
            return false;
        }

        Symbol tmpSymbol = this.table.get(symbol.getIdent());

        if (tmpSymbol instanceof PrototypeSymbol && symbol instanceof FunctionSymbol)
        {
            PrototypeSymbol prototypeSymbol = (PrototypeSymbol)tmpSymbol;
            
            prototypeSymbol.setDefined(true);
        }
        else if (tmpSymbol != null)
        {
            return false;
        }
        else
        {
            this.table.put(symbol.getIdent(), symbol);
        }

        return true;
    }

    public boolean remove(String ident)
    {
        if (ident == null)
        {
            return false;
        }

        return this.table.remove(ident) != null;
    }
    
    public Symbol[] getValuesToArray()
    {
        return this.table.values().toArray(new Symbol[0]);
    }

    public Symbol lookup(String ident)
    {
        if (ident == null)
        {
            return null;
        }

        Symbol result = this.table.get(ident);

        if ((result == null) && (this.parent != null))
        {
            return this.parent.lookup(ident);
        }

        return result;
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

        if (!(obj instanceof SymbolTable))
        {
            return false;
        }
        
        final SymbolTable other = (SymbolTable) obj;

        if (!Objects.equals(this.table, other.table))
        {
            return false;
        }

        return Objects.equals(this.parent, other.parent);
    }
}
