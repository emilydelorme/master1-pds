package TP2.ASD.Statement.Block;

import java.util.List;

import TP2.ASD.VariableFormeInterface;
import TP2.ASD.Ret;
import TP2.ASD.TypeInterface;
import TP2.exceptions.TypeException;

public class Declaration
{
    private TypeInterface type;
    private List<VariableFormeInterface> variablesForme;

    public Declaration(TypeInterface type, List<VariableFormeInterface> idents)
    {
        this.type = type;
        this.variablesForme = idents;
    }

    // Pretty-printer
    public String pp()
    {
        String str = "";

        str += this.type.pp() + " ";

        int identsSize = this.variablesForme.size();

        for (int i = 0; i < identsSize; ++i)
        {
            str += this.variablesForme.get(i).pp();

            if (i < identsSize - 1)
            {
                str += ", ";
            }
        }

        str += "\n";

        return str;
    }

    public Ret toIR() throws TypeException
    {
        return null;
    }
}
