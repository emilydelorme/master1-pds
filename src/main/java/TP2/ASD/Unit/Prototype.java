package TP2.ASD.Unit;

import TP2.ASD.ParameterInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.SymbolTable.FunctionSymbol;
import TP2.SymbolTable.SymbolTable;
import TP2.ASD.UnitInterface;

import java.util.List;

import TP2.ASD.TypeInterface;
import TP2.exceptions.TypeException;

public class Prototype implements UnitInterface
{
    private TypeInterface type;
    private String ident;
    private List<ParameterInterface> parametres;
    
    public Prototype(TypeInterface type, String ident, List<ParameterInterface> parametres)
    {
        this.type = type;
        this.ident = ident;
        this.parametres = parametres;
    }

    public TypeInterface getType()
    {
        return type;
    }

    public String getIdent()
    {
        return ident;
    }

    public List<ParameterInterface> getParametres()
    {
        return parametres;
    }

    @Override
    public String pp()
    {
        StringBuilder strParametres = new StringBuilder();
        
        int parametersSize = this.parametres.size();
        
        for (int i = 0; i < parametersSize; ++i)
        {
            strParametres.append(this.parametres.get(i).pp());
            
            if (i < parametersSize - 1)
            {
                strParametres.append(", ");
            }
        }

        return "PROTO" + " " + this.type.pp() + " " + this.ident + "(" + strParametres + ")" + "";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }

    @Override
    public void exitWithMessage(String message)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkError()
    {
        // TODO Auto-generated method stub
        
    }
}
