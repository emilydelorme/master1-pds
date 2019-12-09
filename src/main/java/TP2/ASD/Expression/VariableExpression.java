package TP2.ASD.Expression;

import TP2.ASD.ErrorHandlerInterface;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Ret.TypeRet;
import TP2.ASD.Types.Int;
import TP2.ASD.VariableForm.Basic;
import TP2.ASD.VariableFormInterface;
import TP2.Llvm.Instructions.load.LoadTab;
import TP2.Llvm.Instructions.load.LoadVar;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.Utils;
import TP2.exceptions.TypeException;
import org.tinylog.Logger;

public class VariableExpression implements ExpressionInterface, ErrorHandlerInterface
{
    private VariableFormInterface expression;
    private SymbolTable symbolTable;

    public VariableExpression(VariableFormInterface expression, SymbolTable symbolTable)
    {
        this.expression = expression;
        this.symbolTable = symbolTable;
    }

    @Override
    public void checkError()
    {
        String variableIdent = this.expression.getIdent();
        Symbol symbol = this.symbolTable.lookup(variableIdent);

        if (!(symbol instanceof VariableSymbol)) {
            exitWithMessage(String.format("[Variable] (%s) unknown variable", variableIdent));
        }
    }

    public VariableFormInterface getExpression()
    {
        return expression;
    }

    public String pp()
    {
        checkError();

        return this.expression.pp();
    }

    public TypeRet toIR()
    {
        checkError();

        TypeRet result = new TypeRet(new Int());
        /*
        String tmpIdent = Utils.newtmp();
        result.getIr().appendCode(new LoadVar(tmpIdent, ((VariableSymbol) symbolTable.lookup(variableForm.getIdent())).getLlvmIdent()));
        result.setResult(tmpIdent);
        */
        try {
            final GenericRet expressionRet = expression.toIR();


            String tmpIdent = Utils.newtmp();
            if (expression instanceof Basic) {
                result.getIr().appendCode(new LoadVar(tmpIdent, expression.getIdent()));
                result.setResult(tmpIdent);
            } else {
                result.getIr().appendAll(expressionRet.getIr())
                        .appendCode(new LoadTab(tmpIdent, expression.getIdent(),
                                expressionRet.getResult(), ((VariableSymbol) symbolTable.lookup(expression.getIdent())).getSize()));
                String resultVal = Utils.newtmp();
                result.getIr().appendCode(new LoadVar(resultVal, tmpIdent));
                result.setResult(resultVal);
            }
        } catch (TypeException e) {
            Logger.error(e.getMessage());
        }
        return result;
    }
}