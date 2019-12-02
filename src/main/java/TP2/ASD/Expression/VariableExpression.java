package TP2.ASD.Expression;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.TypeRet;
import TP2.SymbolTable.SymbolTable;
import TP2.ASD.VariableFormInterface;

public class VariableExpression implements ExpressionInterface
{
    private VariableFormInterface variableForm;
    private SymbolTable symbolTable;

    public VariableExpression(VariableFormInterface variableForm, SymbolTable symbolTable)
    {
        this.variableForm = variableForm;
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