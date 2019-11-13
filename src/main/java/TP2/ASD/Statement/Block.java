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
        
        str += "{";
        str += "\n";
        
        for (StatementInterface statement : this.statements)
        {
            str += statement.pp();
        }
        
        str += "\n";
        str += " }";
        
        return str;
    }

    @Override
    public Ret toIR() throws TypeException {

        return null;
    }
}
