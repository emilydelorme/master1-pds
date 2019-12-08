package TP2.ASD.Statement;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.ASD.VariableFormInterface;
import TP2.SymbolTable.SymbolTable;
import TP2.exceptions.TypeException;

import java.util.List;
import java.util.stream.IntStream;

public class Read implements StatementInterface
{
    private List<VariableFormInterface> variablesForm;

    public Read(List<VariableFormInterface> variablesForm)
    {
        this.variablesForm = variablesForm;
    }
    
    @Override
    public void checkError()
    {
        for (VariableFormInterface variableFormInterface : this.variablesForm)
        {
            variableFormInterface.checkError();
        }
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder str = new StringBuilder("READ ");
        
        int variablesSize = this.variablesForm.size();

        IntStream.range(0, variablesSize).forEach(i -> {
            str.append(this.variablesForm.get(i).pp());
            if (i < variablesSize - 1) {
                str.append(", ");
            }
        });
        
        return str.toString();
    }

    @Override
    public GenericRet toIR(SymbolTable symbolTable) throws TypeException
    {
        checkError();
        
        return null;
    }
}
