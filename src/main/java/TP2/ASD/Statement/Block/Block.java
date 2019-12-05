package TP2.ASD.Statement.Block;

import java.util.List;
import java.util.Optional;

import TP2.ASD.Ret.GenericRet;
import TP2.SymbolTable.SymbolTable;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class Block implements StatementInterface
{
    private Optional<List<Declaration>> declarations;
    private List<StatementInterface> statements;

    public Block(Optional<List<Declaration>> declarations, List<StatementInterface> statements)
    {
        this.declarations = declarations;
        this.statements = statements;
    }
    
    public Block(List<StatementInterface> statements)
    {
        this.declarations = Optional.empty();
        this.statements = statements;
    }
    
    @Override
    public void checkError()
    {
        if (!declarations.isEmpty())
        {
            for (Declaration declaration : this.declarations.get())
            {
                declaration.checkError();
            }
        }
        
        for (StatementInterface statementInterface : this.statements)
        {
            statementInterface.checkError();
        }
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder str = new StringBuilder();

        int statementSize = this.statements.size();

        str.append("{").append("\n");

        if (!declarations.isEmpty())
        {
            for (Declaration declaration : this.declarations.get())
            {
                str.append(declaration.pp());
            }
        }

        for (int i = 0; i < statementSize; ++i)
        {
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
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return null;
    }
}
