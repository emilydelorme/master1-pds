package TP2.ASD.Statement;

import TP2.ASD.Ret.GenericRet;
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
        StringBuilder str = new StringBuilder();
        
        int statementSize = this.statements.size();
        
        str.append("{\n");
        
        for (int i = 0; i < statementSize; ++i)
        {
            str.append("\t");
            str.append(this.statements.get(i).pp());
            
            if (i < statementSize - 1)
            {
                str.append("\n");
            }
        }
        
        str.append("\n");
        str.append("}");
        
        return str.toString();
    }

    @Override
    public GenericRet toIR() throws TypeException {

        return null;
    }
}
