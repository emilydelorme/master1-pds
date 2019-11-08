package TP2.Unit;

import TP2.ASD.UnitInterface;

import java.util.List;

import TP2.ASD.StatementInterface;
import TP2.ASD.TypeInterface;;

// function(type, IDENT, List<parametre>, statement)
public class Function implements UnitInterface
{
    private TypeInterface type;
    private String ident;
    private List<String> parametres;
    private StatementInterface statement;

    public Function(TypeInterface type,String ident,List<String> parametres,StatementInterface statement)
    {
        this.type = type;
        this.ident = ident;
        this.parametres = parametres;
        this.statement = statement;
    }

    // Pretty-printer
    public String pp()
    {
        String strParametres = "";
        
        for (int i= 0; i < this.parametres.size(); ++i)
        {
            //TODO LPOFDSHFISDHGFSDGJHSFDF
        }

        return "FUNC" + " " + this.type.pp() + " " + this.ident + " " + "(" + strParametres + ")" + this.statement.pp();
    }
}
