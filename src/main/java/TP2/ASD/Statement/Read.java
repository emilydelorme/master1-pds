package TP2.ASD.Statement;

import java.util.List;

import TP2.ASD.ItemInterface;
import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.ASD.VariableFormeInterface;
import TP2.exceptions.TypeException;

public class Read implements StatementInterface
{
    private List<VariableFormeInterface> variablesForme;

    public Read(List<VariableFormeInterface> variablesForme)
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
    public Ret toIR() throws TypeException
    {
        return null;
    }

}
