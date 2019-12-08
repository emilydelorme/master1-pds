package TP2.ASD.Statement.Block;

import java.util.List;
import java.util.stream.IntStream;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.ErrorHandlerInterface;
import TP2.ASD.TypeInterface;
import TP2.ASD.VariableFormDeclarationInterface;
import TP2.SymbolTable.SymbolTable;
import TP2.exceptions.TypeException;

public class Declaration implements ErrorHandlerInterface
{
    private TypeInterface type;
    private List<VariableFormDeclarationInterface> variablesForm;

    public Declaration(TypeInterface type, List<VariableFormDeclarationInterface> idents)
    {
        this.type = type;
        this.variablesForm = idents;
    }
    
    @Override
    public void checkError()
    {
        
    }
    
    public String pp()
    {
        StringBuilder str = new StringBuilder();

        str.append(this.type.pp()).append(" ");
        int identsSize = this.variablesForm.size();

        IntStream.range(0, identsSize).forEach(i -> {
            str.append(this.variablesForm.get(i).pp());
            if (i < identsSize - 1) {
                str.append(", ");
            }
        });
        str.append("\n");

        return str.toString();
    }

    public GenericRet toIR() throws TypeException
    {
        GenericRet result = new GenericRet();

        variablesForm.stream()
                .map(var -> var.toIR())
                .forEach(genericRet -> result.getIr().appendAll(genericRet.getIr()));
        return result;
    }
}
