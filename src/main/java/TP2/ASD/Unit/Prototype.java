package TP2.ASD.Unit;

import TP2.ASD.Ret;
import TP2.ASD.UnitInterface;

import java.util.List;

import TP2.ASD.TypeInterface;
import TP2.exceptions.TypeException;;

// prototype(type, IDENT, List<parametre>)
public class Prototype implements UnitInterface
{
    private TypeInterface type;
    private String ident;
    private List<String> parametres;
    
    public Prototype(TypeInterface type, String ident, List<String> parametres)
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
            strParametres += this.parametres.get(i);
            
            if (i < parametersSize - 1)
            {
                strParametres += ", ";
            }
        }

        return "PROTO" + " " + this.type.pp() + " " + this.ident + " " + "(" + strParametres + ")";
    }

    @Override
    public Ret toIR() throws TypeException
    {
        return null;
    }
}
