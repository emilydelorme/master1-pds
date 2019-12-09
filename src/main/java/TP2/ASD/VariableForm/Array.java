package TP2.ASD.VariableForm;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.VariableFormInterface;
import TP2.Llvm.Instructions.load.LoadVar;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.SymbolTable.VariableSymbol;
import TP2.Utils;
import TP2.exceptions.TypeException;

public class Array implements VariableFormInterface
{
    private String ident;
    private String llvmIdent;
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
    public String getLlvmIdent() {
        return this.llvmIdent;
    }

    @Override
    public void checkError()
    {
        Symbol symbol = this.symbolTable.lookup(this.ident);
        
        if (!(symbol instanceof VariableSymbol))
        {
            exitWithMessage(String.format("[Variable] (%s) unknown variable", this.ident));
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
        GenericRet result = new GenericRet();

        result.getIr().appendAll(expression.toIR().getIr());

        String tmpIdent = Utils.newtmp();

        result.getIr().appendCode(new LoadVar(tmpIdent, ((VariableSymbol) symbolTable.lookup(this.ident)).getLlvmIdent()));
        result.setResult(tmpIdent);
        return result;
    }
}
