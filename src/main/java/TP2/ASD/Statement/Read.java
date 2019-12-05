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
    public void checkError()
    {
        for (VariableFormInterface variableFormInterface : this.variablesForme)
        {
            variableFormInterface.checkError();
        }
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder str = new StringBuilder("READ ");
        
        int variablesSize = this.variablesForme.size();
        
        for(int i = 0; i < variablesSize; ++i)
        {

            str.append(this.variablesForme.get(i).pp());

            if (i < variablesSize - 1)
            {
                str.append(", ");
            }
        }
        
        return str.toString();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return null;
    }
}
