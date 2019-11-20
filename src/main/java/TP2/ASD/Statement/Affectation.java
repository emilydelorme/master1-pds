package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret;
import TP2.ASD.StatementInterface;
import TP2.ASD.VariableFormeInterface;
import TP2.exceptions.TypeException;

public class Affectation implements StatementInterface
{
    private VariableFormeInterface variableForme;
    private ExpressionInterface expression;

    public Affectation(VariableFormeInterface variableForme, ExpressionInterface expression)
    {
        this.variableForme = variableForme;
        this.expression = expression;
    }

    // Pretty-printer
    @Override
    public String pp()
    {
        return this.variableForme.pp() + " := " + this.expression.pp();
    }

    @Override
    public Ret toIR() throws TypeException
    {
        return null;
    }
}