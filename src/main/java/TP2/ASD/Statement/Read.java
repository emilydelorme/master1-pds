package TP2.ASD.Statement;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.ASD.VariableFormInterface;
import TP2.exceptions.TypeException;

import java.util.List;

public class Read implements StatementInterface
{
    private List<VariableFormInterface> variablesForme;

    public Read(List<VariableFormInterface> variablesForme)
    {
        this.variablesForme = variablesForme;
    }

    @Override
    public String pp()
    {
        String str = "READ ";
        
        int variablesSize = this.variablesForme.size();
        
        for(int i = 0; i < variablesSize; ++i)
        {

            str += this.variablesForme.get(i).pp();

            if (i < variablesSize - 1)
            {
                str += ", ";
            }
        }
        
        return str;
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }

}
