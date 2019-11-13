package TP2.ASD.Statement;

import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

import java.util.List;

public class Block implements StatementInterface
{
    private List<StatementInterface> statements;

    public Block(List<StatementInterface> statements)
    {
        this.statements = statements;
    }

    @Override
    public String pp() {
        String str = "";
        
        int statementSize = this.statements.size();
        
        str += "{";
        str += "\n";
        
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
    public Ret toIR() throws TypeException {

        return null;
    }
}
