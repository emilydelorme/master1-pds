package TP2.ASD.Statement;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.ASD.VariableFormInterface;
import TP2.Llvm.Instructions.io.ReadCall;
import TP2.Llvm.Instructions.io.ReadHeader;
import TP2.TypeLabel;
import TP2.Utils;
import TP2.exceptions.TypeException;

import java.util.List;
import java.util.stream.IntStream;

public class Read implements StatementInterface
{
    private List<VariableFormInterface> variables;

    public Read(List<VariableFormInterface> variables)
    {
        this.variables = variables;
    }
    
    @Override
    public void checkError()
    {
        for (VariableFormInterface variableFormInterface : this.variables)
        {
            variableFormInterface.checkError();
        }
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder str = new StringBuilder("READ ");
        
        int variablesSize = this.variables.size();

        IntStream.range(0, variablesSize).forEach(i -> {
            str.append(this.variables.get(i).pp());
            if (i < variablesSize - 1) {
                str.append(", ");
            }
        });
        
        return str.toString();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();

        GenericRet result = new GenericRet();

        for(VariableFormInterface variable : variables) {
            String readName = Utils.newLabel(TypeLabel.FMT);

            result.getIr().appendHeader(new ReadHeader(readName))
                  .appendCode(new ReadCall(readName, "%" + variable.getIdent() + "var"));
        }

        return result;
    }
}
