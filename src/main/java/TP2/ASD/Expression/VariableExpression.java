package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.TypeRet;
import TP2.SymbolTable.SymbolTable;
import TP2.ASD.VariableFormInterface;

public class VariableExpression implements ExpressionInterface
{
    private VariableFormInterface variable;
    private SymbolTable symbolTable;

    public VariableExpression(VariableFormInterface variable, SymbolTable symbolTable)
    {
        this.variable = variable;
        this.symbolTable = symbolTable;
    }

    public String pp()
    {
        return this.variable.pp();
    }

    public TypeRet toIR()
    {
        return null;
    }
}