package TP2.ASD.Statement.Block;

import java.util.List;
import java.util.Optional;

import TP2.ASD.Ret.GenericRet;
import TP2.SymbolTable.SymbolTable;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class Block implements StatementInterface
{
    private Optional<Declaration> declaration;
    private List<StatementInterface> statements;
    private SymbolTable symbolTable;

    public Block(Optional<Declaration> declaration, List<StatementInterface> statements, SymbolTable symbolTable)
    {
        this.declaration = declaration;
        this.statements = statements;
        this.symbolTable = symbolTable;
    }
    
    public Block(List<StatementInterface> statements, SymbolTable symbolTable)
    {
        this.declaration = Optional.empty();
        this.statements = statements;
        this.symbolTable = symbolTable;
    }

    @Override
    public String pp()
    {
        StringBuilder str = new StringBuilder();

        int statementSize = this.statements.size();

        str.append("{").append("\n");

        this.declaration.ifPresent(value -> str.append(value.pp()));

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

        return null;
    }
}
