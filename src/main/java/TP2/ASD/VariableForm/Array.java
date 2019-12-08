package TP2.ASD.VariableForm;

import TP2.ASD.Expression.IntegerExpression;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.VariableFormInterface;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.exceptions.TypeException;

public class Array implements VariableFormInterface
{
    private String ident;
    private ExpressionInterface expression;
    private SymbolTable symbolTable;

    public Array(String ident, ExpressionInterface expression, SymbolTable symbolTable)
    {
        this.ident = ident;
        this.expression = expression;
        this.symbolTable = symbolTable;
    }
    
    @Override
    public String getIdent()
    {
        return this.ident;
    }
    
    @Override
    public void checkError()
    {
        Symbol symbol = this.symbolTable.lookup(this.ident);
        
        if (!(symbol instanceof VariableSymbol))
        {
            exitWithMessage(String.format("[Variable array] (%s) unknown variable", this.ident));
        }
        
        if (this.expression instanceof IntegerExpression)
        {
            IntegerExpression integerExpression = (IntegerExpression)this.expression;
            VariableSymbol variableSymbol = (VariableSymbol)symbol;
            
            int integerValue = integerExpression.getValue();
            
            if (integerValue > variableSymbol.getSize() - 1)
            {
                exitWithMessage(String.format("[Variable array] (%s) index out of bounds %d", this.ident, integerValue));
            }
        }

        this.expression.checkError();
    }
    
    @Override
    public String pp()
    {
        checkError();
        
        return this.ident + "[" + this.expression.pp() + "]";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return new GenericRet();
    }
}
