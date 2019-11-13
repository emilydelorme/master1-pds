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
        return "{" +
                    statements.stream()
                    .map(StatementInterface::pp)
                    .reduce((statement1, statement2) -> statement1 + "\n" + statement2) +
               "}";
    }

    @Override
    public Ret toIR() throws TypeException
    {
        return null;
    }
}
