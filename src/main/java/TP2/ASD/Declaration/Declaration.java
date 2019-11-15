package TP2.ASD.Declaration;

import java.util.List;

import TP2.ASD.DeclarationInterface;
import TP2.ASD.Ret;
import TP2.ASD.TypeInterface;
import TP2.exceptions.TypeException;

public class Declaration
{
    private TypeInterface type;
    private List<DeclarationInterface> idents;

    public Declaration(TypeInterface type, List<DeclarationInterface> idents)
    {
        this.type = type;
        this.idents = idents;
    }

    // Pretty-printer
    public String pp()
    {
        String str = "";

        str += "\t";
        str += this.type.pp() + " ";

        int identsSize = this.idents.size();

        for (int i = 0; i < identsSize; ++i)
        {
            str += this.idents.get(i).pp();

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
