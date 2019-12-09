package TP2.SymbolTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public boolean isPresent(String ident) {
        return Objects.isNull(lookup(ident));
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof SymbolTable)) return false;
        SymbolTable that = (SymbolTable) o;
        return Objects.equals(table, that.table) &&
                Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(table, parent);
    }
}
