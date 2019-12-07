package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.SymbolTable.FunctionSymbol;
import TP2.SymbolTable.PrototypeSymbol;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.ASD.StatementInterface;
import TP2.ASD.VariableFormInterface;
import TP2.ASD.Expression.VariableExpression;
import TP2.ASD.Types.Void;
import TP2.ASD.VariableForm.Array;
import TP2.ASD.VariableForm.Basic;
import TP2.exceptions.TypeException;

public class Affectation implements StatementInterface
{
    private VariableFormInterface variableForme;
    private ExpressionInterface expression;
    private SymbolTable symbolTable;

    public Affectation(VariableFormInterface variableForme, ExpressionInterface expression, SymbolTable symbolTable)
    {
        this.variableForme = variableForme;
        this.expression = expression;
        this.symbolTable = symbolTable;
    }
    
    @Override
    public void checkError()
    {
        this.variableForme.checkError();
        this.expression.checkError();

        if (!(this.variableForme instanceof Array))
        {
            Symbol symbol = this.symbolTable.lookup(this.variableForme.getIdent());
            
            if (symbol instanceof VariableSymbol)
            {
                VariableSymbol variableSymbol = (VariableSymbol)symbol;
                
                if (variableSymbol.isArray())
                {
                    exitWithMessage(String.format("[Affectation] (%s) needs to be called as an array", variableSymbol.getIdent()));
                }
            }
        }
        
        if (this.variableForme instanceof Array)
        {
            Symbol symbol = this.symbolTable.lookup(this.variableForme.getIdent());
            
            if (symbol instanceof VariableSymbol)
            {
                VariableSymbol variableSymbol = (VariableSymbol)symbol;
                
                if (!variableSymbol.isArray())
                {
                    exitWithMessage(String.format("[Affectation] (%s) needs to be a normal variable", variableSymbol.getIdent()));
                }
            }
        }
        
        /*
        if (this.variableForme instanceof Basic)
        {
            if (this.expression instanceof VariableExpression)
            {
                VariableExpression variableExpression = (VariableExpression)this.expression;
                
                Symbol symbol = this.symbolTable.lookup(variableExpression.getVariableForm().getIdent());
                
                if (symbol instanceof VariableSymbol)
                {
                    VariableSymbol variableSymbol = (VariableSymbol)symbol;
                    
                    if (variableSymbol.isArray())
                    {
                        exitWithMessage(String.format("[Affectation] (%s) needs to be a normal variable", variableSymbol.getIdent()));
                    }
                }
            }
        }
        */
        
        if (this.expression instanceof FunctionCall)
        {
            FunctionCall functionCall = (FunctionCall)this.expression;
            
            Symbol symbol = this.symbolTable.lookup(functionCall.getFuncIdent());
            
            if (symbol instanceof FunctionSymbol)
            {
                FunctionSymbol functionSymbol = (FunctionSymbol)symbol;

                if (functionSymbol.getReturnType().equals(new Void()))
                {
                    exitWithMessage(String.format("[Affectation] (%s) can't call a [%s] function", functionSymbol.getIdent(), functionSymbol.getReturnType().getType()));
                }
            }
            
            if (symbol instanceof PrototypeSymbol)
            {
                PrototypeSymbol prototypeSymbol = (PrototypeSymbol)symbol;

                if (prototypeSymbol.getReturnType().equals(new Void()))
                {
                    exitWithMessage(String.format("[Affectation] (%s) can't call a [%s] function", prototypeSymbol.getIdent(), prototypeSymbol.getReturnType().getType()));
                }
            }
        }
    }

    @Override
    public String pp()
    {
        checkError();
        
        return this.variableForme.pp() + " := " + this.expression.pp();
    }

    @Override
    public GenericRet toIR(SymbolTable symbolTable) throws TypeException
    {
        checkError();
        
        return null;
    }
}