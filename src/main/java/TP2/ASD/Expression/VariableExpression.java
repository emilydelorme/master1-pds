package TP2.ASD.Expression;

import TP2.ASD.ErrorHandlerInterface;
import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Ret.TypeRet;
import TP2.ASD.Types.Int;
import TP2.Llvm.Instructions.load.LoadVar;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.ASD.VariableFormInterface;
import TP2.Utils;

public class VariableExpression implements ExpressionInterface, ErrorHandlerInterface {
    private VariableFormInterface variableForm;
    private SymbolTable symbolTable;

    public VariableExpression(VariableFormInterface variableForm, SymbolTable symbolTable) {
        this.variableForm = variableForm;
        this.symbolTable = symbolTable;
    }

    @Override
    public void checkError() {
        String variableIdent = this.variableForm.getIdent();
        Symbol symbol = this.symbolTable.lookup(variableIdent);

        if (!(symbol instanceof VariableSymbol)) {
            exitWithMessage(String.format("[Variable] (%s) unknown variable", variableIdent));
        }
    }

    public VariableFormInterface getVariableForm() {
        return variableForm;
    }

    public String pp() {
        checkError();

        return this.variableForm.pp();
    }

    public TypeRet toIR() {
        checkError();

        TypeRet result = new TypeRet(new Int());
        String tmpIdent = Utils.newtmp();
        result.getIr().appendCode(new LoadVar(tmpIdent, variableForm.getIdent()));
        result.setResult(tmpIdent);

        return result;
    }
}