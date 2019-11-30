package TP2.ASD.Unit;

import TP2.ASD.ParameterInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.UnitInterface;

import java.util.List;

import TP2.ASD.TypeInterface;
import TP2.exceptions.TypeException;;

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

    @Override
    public String pp()
    {
        String strParametres = "";

        int parametersSize = this.parametres.size();
        
        for (int i = 0; i < parametersSize; ++i)
        {
            strParametres += this.parametres.get(i).pp();
            
            if (i < parametersSize - 1)
            {
                strParametres += ", ";
            }
        }

        return "PROTO" + " " + this.type.pp() + " " + this.ident + "(" + strParametres + ")" + "";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}
