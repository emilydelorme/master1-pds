package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.TypeRet;
import TP2.SymbolTable.SymbolTable;
import TP2.ASD.VariableFormInterface;

public class VariableExpression implements ExpressionInterface
{
    private VariableFormInterface variableForm;
    private SymbolTable symbolTable;

    public VariableExpression(VariableFormInterface variable, SymbolTable symbolTable)
    {
        this.variableForm = variable;
        this.symbolTable = symbolTable;
    }

    public VariableFormInterface getVariableForm()
    {
        return variableForm;
    }

    public String pp()
    {
        return this.variableForm.pp();
    }

    public TypeRet toIR()
    {
        return null;
    }
}