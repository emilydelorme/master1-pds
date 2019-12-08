package TP2.ASD.Statement;

import java.util.ArrayList;
import java.util.List;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Ret.TypeRet;
import TP2.ASD.Types.Void;
import TP2.Llvm.Instructions.functions.CallFunction;
import TP2.Llvm.Types.LlvmVoid;
import TP2.SymbolTable.FunctionSymbol;
import TP2.SymbolTable.PrototypeSymbol;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.ASD.StatementInterface;
import TP2.ASD.Expression.VariableExpression;
import TP2.exceptions.TypeException;

public class FunctionCall implements StatementInterface, ExpressionInterface
{
    private String functionIdent;
    private List<ExpressionInterface> expressions;
    private SymbolTable symbolTable;

    public FunctionCall(String functionIdent, List<ExpressionInterface> expressions, SymbolTable symbolTable)
    {
        this.functionIdent = functionIdent;
        this.expressions = expressions;
        this.symbolTable = symbolTable;
    }

    public String getFunctionIdent()
    {
        return functionIdent;
    }

    @Override
    public void checkError()
    {
        Symbol symbol = this.symbolTable.lookup(this.functionIdent);

        if (!(symbol instanceof PrototypeSymbol) && !(symbol instanceof FunctionSymbol))
        {
            exitWithMessage(String.format("[Function call] (%s) unknown function name", this.functionIdent));
        }
        
        if (symbol instanceof PrototypeSymbol)
        {
            PrototypeSymbol prototypeSymbol = (PrototypeSymbol)symbol;

            if (!prototypeSymbol.isDefined())
            {
                exitWithMessage(String.format("[Function call] (%s) function not defined", this.functionIdent));
            }
            
            if (prototypeSymbol.getArguments().size() != this.expressions.size())
            {
                exitWithMessage(String.format("[Function call] (%s) mismatch parameters number", this.functionIdent));
            }
            
            int argumentsSize = prototypeSymbol.getArguments().size();
            
            for (int i = 0; i < argumentsSize; ++i)
            {
                VariableSymbol prototypeVariableSymbol = prototypeSymbol.getArguments().get(i);
                ExpressionInterface currentCallParameter = this.expressions.get(i);

                if (currentCallParameter instanceof VariableExpression)
                {
                    VariableSymbol functionCallParameterVariableSymbol = (VariableSymbol)this.symbolTable.lookup(((VariableExpression)this.expressions.get(i)).getVariableForm().getIdent());
                    
                    if (prototypeVariableSymbol.isArray() && !functionCallParameterVariableSymbol.isArray())
                    {
                        exitWithMessage(String.format("[Function call] (%s) parameter (%s) needs to be an array", this.functionIdent, functionCallParameterVariableSymbol.getIdent()));
                    }
/*
                    if (!prototypeVariableSymbol.isArray() && functionCallParameterVariableSymbol.isArray())
                    {
                        exitWithMessage(String.format("[Function call] (%s) parameter (%s) needs to be a normal variable", this.funcIdent, functionCallParameterVariableSymbol.getIdent()));
                    }*/
                }
                else if (!(currentCallParameter instanceof VariableExpression) && prototypeVariableSymbol.isArray())
                {
                    exitWithMessage(String.format("[Function call] (%s) parameter (%s) needs to be an array", this.functionIdent, currentCallParameter.pp()));
                }
            }
        }
        
        if (symbol instanceof FunctionSymbol)
        {
            FunctionSymbol functionSymbol = (FunctionSymbol)symbol;

            if (functionSymbol.getArguments().size() != this.expressions.size())
            {
                exitWithMessage(String.format("[Function call] (%s) mismatch parameters number", this.functionIdent));
            }
            
            int argumentsSize = functionSymbol.getArguments().size();
            
            for (int i = 0; i < argumentsSize; ++i)
            {
                VariableSymbol prototypeVariableSymbol = functionSymbol.getArguments().get(i);
                ExpressionInterface currentCallParameter = this.expressions.get(i);

                if (currentCallParameter instanceof VariableExpression)
                {
                    VariableSymbol functionCallParameterVariableSymbol = (VariableSymbol)this.symbolTable.lookup(((VariableExpression)this.expressions.get(i)).getVariableForm().getIdent());

                    if (prototypeVariableSymbol.isArray() && !functionCallParameterVariableSymbol.isArray())
                    {
                        exitWithMessage(String.format("[Function call] (%s) parameter (%s) needs to be an array", this.functionIdent, functionCallParameterVariableSymbol.getIdent()));
                    }
                    
                    if (!prototypeVariableSymbol.isArray() && functionCallParameterVariableSymbol.isArray())
                    {
                        exitWithMessage(String.format("[Function call] (%s) parameter (%s) needs to be a normal variable", this.functionIdent, functionCallParameterVariableSymbol.getIdent()));
                    }
                }
                else if (!(currentCallParameter instanceof VariableExpression) && prototypeVariableSymbol.isArray())
                {
                    exitWithMessage(String.format("[Function call] (%s) parameter (%s) needs to be an array", this.functionIdent, currentCallParameter.pp()));
                }
            }
        }
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder str = new StringBuilder(this.functionIdent);
        
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
        checkError();

        TypeRet result = new TypeRet(new Void());

        List<String> variables = new ArrayList<>();

        for (ExpressionInterface expression : expressions) {
            GenericRet expressionRet = expression.toIR();
            variables.add(expressionRet.getResult());
            result.getIr().appendAll(expressionRet.getIr());
        }

        result.getIr().appendCode(new CallFunction(result.getType().toLlvmType(), functionIdent, variables));

        return result;
    }

    @Override
    public TypeRet toIR(SymbolTable symbolTable) throws TypeException
    {
       return this.toIR();
    }
}