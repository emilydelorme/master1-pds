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

        if (this.table.containsKey(symbol.getIdent()))
        {
            return false;
        }

        this.table.put(symbol.getIdent(), symbol);

        return true;
    }

    public boolean remove(String ident)
    {

        if (ident == null)
        {
            return false;
        }

        if (this.table.remove(ident) == null)
        {
            return false;
        }

        return true;
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

        if (!Objects.equals(this.parent, other.parent))
        {
            return false;
        }

        return true;
    }
}