package TP2.ASD.Statement.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import TP2.ASD.Ret.GenericRet;
import TP2.Llvm.InstructionHandler;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.Utils;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class Block implements StatementInterface
{
    private List<Declaration> declarations;
    private List<StatementInterface> statements;
    public static int identLevel = 0;

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

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder str = new StringBuilder();
        int statementSize = this.statements.size();

        str.append("{").append("\n");
        identLevel++;

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
        identLevel--;
        str.append(Utils.indent(identLevel)).append("}");

        return str.toString();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();

        GenericRet result = new GenericRet();

        for (Declaration declaration : declarations) {
            result.getIr().appendAll(declaration.toIR().getIr());
        }

        for (StatementInterface statement : statements) {
            result.getIr().appendAll(statement.toIR().getIr());
        }

        return result;
    }
}
