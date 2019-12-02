package TP2.ASD.Statement;

import java.util.List;

import TP2.ErrorHandlerInterface;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.TypeRet;
import TP2.ASD.VariableForm.Array;
import TP2.SymbolTable.PrototypeSymbol;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.ASD.StatementInterface;
import TP2.ASD.Expression.VariableExpression;
import TP2.exceptions.TypeException;

public class FunctionCall implements StatementInterface, ExpressionInterface, ErrorHandlerInterface
{
    private String funcIdent;
    private List<ExpressionInterface> expressions;
    private SymbolTable symbolTable;

    public FunctionCall(String funcIdent, List<ExpressionInterface> expressions, SymbolTable symbolTable)
    {
        this.funcIdent = funcIdent;
        this.expressions = expressions;
        this.symbolTable = symbolTable;
    }
    
    @Override
    public void exitWithMessage(String message)
    {
        System.err.println("ERROR: " + message);
        
        System.exit(1);
    }

    @Override
    public void checkError()
    {
        Symbol symbol = this.symbolTable.lookup(this.funcIdent);

        if (symbol == null)
        {
            exitWithMessage(String.format("unknown function call (%s)", this.funcIdent));
        }
        
        if (symbol instanceof PrototypeSymbol)
        {
            PrototypeSymbol prototypeSymbol = (PrototypeSymbol)symbol;
            
            if (!prototypeSymbol.isDefined())
            {
                exitWithMessage(String.format("function not defined (%s)", this.funcIdent));
            }
            
            if (prototypeSymbol.getArguments().size() != this.expressions.size())
            {
                exitWithMessage("wrong parameters number");
            }
            
            int argumentsSize = prototypeSymbol.getArguments().size();
            
            for (int i = 0; i < argumentsSize; ++i)
            {
                ExpressionInterface tmpExpression = this.expressions.get(i);

                if (tmpExpression instanceof VariableExpression)
                {
                    VariableExpression variableExpression = (VariableExpression)tmpExpression;
                    
                    Symbol tmpSymbol = this.symbolTable.lookup(variableExpression.getVariableForm().getIdent());
                    
                    if (tmpSymbol instanceof VariableSymbol)
                    {
                        VariableSymbol variableSymbol = (VariableSymbol)tmpSymbol;
                        
                        if (prototypeSymbol.getArguments().get(i).isArray() && !variableSymbol.isArray())
                        {
                            exitWithMessage(String.format("wrong parameter form, needed an array on parameter number (%d)", i));
                        }
                        
                        if (!prototypeSymbol.getArguments().get(i).isArray() && variableSymbol.isArray())
                        {
                            exitWithMessage(String.format("wrong parameter form, needed a basic variable on parameter number (%d)", i));
                        }
                    }
                }
                
                // TODO: check si c'est autre chose qu'une VariableExpression alors qu'on veut un array
            }
        }
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder str = new StringBuilder(this.funcIdent);
        
        str.append("(");
        
        int variablesSize = this.expressions.size();
        
        for(int i = 0; i < variablesSize; ++i)
        {

            str.append(this.expressions.get(i).pp());

            if (i < variablesSize - 1)
            {
                str.append(", ");
            }
        }
        
        str.append(")");
        
        return str.toString();
    }

    @Override
    public TypeRet toIR() throws TypeException
    {
        return null;
    }
}