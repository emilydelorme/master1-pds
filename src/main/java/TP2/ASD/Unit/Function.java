package TP2.ASD.Unit;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.UnitInterface;

import java.util.List;

import TP2.ASD.StatementInterface;
import TP2.ASD.TypeInterface;
import TP2.exceptions.TypeException;;

// function(type, IDENT, List<parametre>, statement)
public class Function implements UnitInterface
{
    private TypeInterface type;
    private String ident;
    private List<ParameterInterface> parametres;
    private StatementInterface statement;

    public Function(TypeInterface type, String ident, List<ParameterInterface> parametres, StatementInterface statement)
    {
        this.type = type;
        this.ident = ident;
        this.parametres = parametres;
        this.statement = statement;
    }

    // Pretty-printer
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

        return "FUNC" + " " + this.type.pp() + " " + this.ident + "(" + strParametres + ")" + "\n" + this.statement.pp();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }
}
