package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.VariableForm.Array;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.ASD.StatementInterface;
import TP2.ASD.Expression.VariableExpression;
import TP2.exceptions.TypeException;

public class Return implements StatementInterface
{
    private ExpressionInterface expression;
    private SymbolTable symbolTable;
    
    public Return(ExpressionInterface expression, SymbolTable symbolTable)
    {
        this.expression = expression;
        this.symbolTable = symbolTable;
    }
    
    @Override
    public void checkError()
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
                    exitWithMessage(String.format("[Return] (%s) needs to be a normal variable", variableExpression.getVariableForm().getIdent()));
                }
            }
        }
        
        this.expression.checkError();
    }
    
    @Override
    public String pp()
    {
        checkError();
        
        return "RETURN" + " " + this.expression.pp();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return null;
    }
}
