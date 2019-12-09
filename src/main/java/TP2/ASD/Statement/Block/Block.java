package TP2.ASD.Statement.Block;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Statement.StatementUtils;
import TP2.ASD.StatementInterface;
import TP2.Utils;
import TP2.exceptions.TypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Block implements StatementInterface
{
    private List<Declaration> declarations;
    private List<StatementInterface> statements;
    private static int identLevel = 0;

    public Block(List<Declaration> declarations, List<StatementInterface> statements)
    {
        this.declarations = declarations;
        this.statements = statements;
    }
    
    public Block(List<StatementInterface> statements)
    {
        this.declarations = new ArrayList<>();
        this.statements = statements;
    }
    
    @Override
    public void checkError()
    {
        if (!declarations.isEmpty())
        {
            for (Declaration declaration : this.declarations)
            {
                declaration.checkError();
            }
        }
        
        for (StatementInterface statementInterface : this.statements)
        {
            statementInterface.checkError();
        }
    }

    public static int getIdentLevel()
    {
        return identLevel;
    }

    public static void setIdentLevel(int identLevel)
    {
        Block.identLevel = identLevel;
    }

    public static void identLevelPlus()
    {
        identLevel++;
    }

    public static void identLevelSub()
    {
        identLevel--;
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder str = new StringBuilder();
        int statementSize = this.statements.size();

        str.append("{").append("\n");
        Block.identLevelPlus();

        if (!declarations.isEmpty())
        {
            this.declarations.forEach(declaration -> str.append(Utils.indent(identLevel)).append(declaration.pp()));
        }

        IntStream.range(0, statementSize).forEach(i -> {
            str.append(Utils.indent(identLevel)).append(this.statements.get(i).pp());
            if (i < statementSize - 1) {
                str.append("\n");
            }
        });
        
        str.append("\n");
        Block.identLevelSub();
        str.append(Utils.indent(identLevel)).append("}");

        return str.toString();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        StatementUtils.setCurrentBlockLevel(StatementUtils.getCurrentBlockLevel() + 1);
        
        GenericRet result = new GenericRet();

        for (Declaration declaration : declarations) {
            result.getIr().appendAll(declaration.toIR().getIr());
        }

        for (StatementInterface statement : statements) {
            result.getIr().appendAll(statement.toIR().getIr());
        }

        StatementUtils.setCurrentBlockLevel(StatementUtils.getCurrentBlockLevel() - 1);

        return result;
    }
}
