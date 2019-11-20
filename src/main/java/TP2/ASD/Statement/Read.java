package TP2.ASD.Statement;

import java.util.List;

import TP2.ASD.ItemInterface;
import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class Read implements StatementInterface
{
    private List<String> variables;

    public Read(List<String> items)
    {
        this.variables = items;
    }

    @Override
    public String pp()
    {
        String str = "READ ";
        
        int variablesSize = this.variables.size();
        
        for(int i = 0; i < variablesSize; ++i)
        {

            str += this.variables.get(i);

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
