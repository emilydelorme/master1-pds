package TP2.ASD.Unit;

import TP2.ASD.ParameterInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.SymbolTable.FunctionSymbol;
import TP2.SymbolTable.PrototypeSymbol;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.ASD.UnitInterface;
import TP2.ASD.Parameter.Array;

import java.util.List;
import java.util.stream.IntStream;

import TP2.ASD.StatementInterface;
import TP2.ASD.TypeInterface;
import TP2.exceptions.TypeException;

public class Function implements UnitInterface
{
    private TypeInterface type;
    private String ident;
    private List<ParameterInterface> arguments;
    private StatementInterface statement;
    private SymbolTable symbolTable;

    public Function(TypeInterface type, String ident, List<ParameterInterface> arguments, StatementInterface statement, SymbolTable symbolTable)
    {
        this.type = type;
        this.ident = ident;
        this.arguments = arguments;
        this.statement = statement;
        this.symbolTable = symbolTable;
    }

    public TypeInterface getType()
    {
        return type;
    }

    public String getIdent()
    {
        return ident;
    }

    public List<ParameterInterface> getArguments()
    {
        return arguments;
    }

    public StatementInterface getStatement()
    {
        return statement;
    }

    @Override
    public void checkError()
    {
        Symbol symbol = this.symbolTable.lookup(this.ident);
        
        // TOTO: remake this
        if(!this.ident.equals("main"))
        {
            if (symbol instanceof PrototypeSymbol)
            {
                if (!(symbol instanceof PrototypeSymbol))
                {
                    exitWithMessage(String.format("[Function definition] (%s) unknown function definition", this.ident));
                }
                
                PrototypeSymbol prototypeSymbol = (PrototypeSymbol)symbol;
                
                if (!prototypeSymbol.getReturnType().equals(this.type))
                {
                    exitWithMessage(String.format("[Function definition] (%s) mismatch return type with the function prototype", this.ident));
                }
                
                int argsSize = this.arguments.size();
                
                if (argsSize != prototypeSymbol.getArguments().size())
                {
                    exitWithMessage(String.format("[Function definition] (%s) mismatch arguments number with the function prototype", this.ident));
                }
                
                for (int i = 0; i < argsSize; ++i)
                {
                    if (this.arguments.get(i) instanceof Array && !prototypeSymbol.getArguments().get(i).isArray() ||
                        !(this.arguments.get(i) instanceof Array) && prototypeSymbol.getArguments().get(i).isArray())
                    {
                        exitWithMessage(String.format("[Function definition] (%s) (%s) mismatch argument form with the function prototype", this.arguments.get(i).getIdent(), prototypeSymbol.getArguments().get(i).getIdent()));
                    }
                }
            }

            if (symbol instanceof FunctionSymbol)
            {
                FunctionSymbol functionSymbol = (FunctionSymbol)symbol;
                
                if (!functionSymbol.getReturnType().equals(this.type))
                {
                    exitWithMessage(String.format("[Function definition] (%s) mismatch return type with the function prototype", this.ident));
                }
                
                int argsSize = this.arguments.size();
                
                if (argsSize != functionSymbol.getArguments().size())
                {
                    exitWithMessage(String.format("[Function definition] (%s) mismatch arguments number with the function prototype", this.ident));
                }
                
                for (int i = 0; i < argsSize; ++i)
                {
                    if (this.arguments.get(i) instanceof Array && !functionSymbol.getArguments().get(i).isArray() ||
                        !(this.arguments.get(i) instanceof Array) && functionSymbol.getArguments().get(i).isArray())
                    {
                        exitWithMessage(String.format("[Function definition] (%s) (%s) mismatch argument form with the function prototype", this.arguments.get(i).getIdent(), functionSymbol.getArguments().get(i).getIdent()));
                    }
                }
            }
        }

        this.statement.checkError();
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder strParameters = new StringBuilder();
        int parametersSize = this.arguments.size();

        IntStream.range(0, parametersSize).forEach(i -> {
            strParameters.append(this.arguments.get(i).pp());
            if (i < parametersSize - 1) {
                strParameters.append(", ");
            }
        });

        return "FUNC" + " " + this.type.pp() + " " + this.ident + "(" + strParameters + ")" + "\n" + this.statement.pp();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return null;
    }
}
