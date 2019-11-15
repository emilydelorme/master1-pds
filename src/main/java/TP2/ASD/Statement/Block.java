package TP2.ASD.Statement;

import java.util.List;

import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.ASD.Declaration.Declaration;
import TP2.exceptions.TypeException;

public class Block implements StatementInterface
{
    private Declaration declaration;
    private List<StatementInterface> statements;

    public Block(Declaration declaration, List<StatementInterface> statements)
    {
        this.declaration = declaration;
        this.statements = statements;
    }

    @Override
    public String pp()
    {
        String str = "";

        int statementSize = this.statements.size();

        str += "{";
        str += "\n";

        str += this.declaration.pp();

        for (int i = 0; i < statementSize; ++i)
        {
            str += "\t";
            str += this.statements.get(i).pp();

            if (i < statementSize - 1)
            {
                str += "\n";
            }
        }

        str += "\n";
        str += "}";

        return str;
    }

    @Override
    public Ret toIR() throws TypeException
    {

        return null;
    }
}
