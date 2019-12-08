package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.ASD.Types.Void;
import TP2.ASD.VariableForm.Array;
import TP2.ASD.VariableFormInterface;
import TP2.Llvm.Instructions.Store;
import TP2.Llvm.Instructions.load.LoadTab;
import TP2.Llvm.Types.LlvmInt;
import TP2.SymbolTable.*;
import TP2.Utils;
import TP2.exceptions.TypeException;

public class Affectation implements StatementInterface {
    private VariableFormInterface leftVar;
    private ExpressionInterface expression;
    private SymbolTable symbolTable;

    public Affectation(VariableFormInterface leftVar, ExpressionInterface expression, SymbolTable symbolTable) {
        this.leftVar = leftVar;
        this.expression = expression;
        this.symbolTable = symbolTable;
    }

    @Override
    public void checkError() {
        this.leftVar.checkError();
        this.expression.checkError();

        if (!(this.leftVar instanceof Array)) {
            Symbol symbol = this.symbolTable.lookup(this.leftVar.getIdent());

            if (symbol instanceof VariableSymbol) {
                VariableSymbol variableSymbol = (VariableSymbol) symbol;

                if (variableSymbol.isArray()) {
                    exitWithMessage(String.format("[Affectation] (%s) needs to be called as an array",
                                                  variableSymbol.getIdent()));
                }
            }
        }

        if (this.leftVar instanceof Array) {
            Symbol symbol = this.symbolTable.lookup(this.leftVar.getIdent());

            if (symbol instanceof VariableSymbol) {
                VariableSymbol variableSymbol = (VariableSymbol) symbol;

                if (!variableSymbol.isArray()) {
                    exitWithMessage(String.format("[Affectation] (%s) needs to be a normal variable",
                                                  variableSymbol.getIdent()));
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
                        exitWithMessage(String.format("[Affectation] (%s) needs to be a normal variable",
                        variableSymbol.getIdent()));
                    }
                }
            }
        }
        */

        if (this.expression instanceof FunctionCall) {
            FunctionCall functionCall = (FunctionCall) this.expression;

            Symbol symbol = this.symbolTable.lookup(functionCall.getFunctionIdent());

            if (symbol instanceof FunctionSymbol) {
                FunctionSymbol functionSymbol = (FunctionSymbol) symbol;

                if (functionSymbol.getReturnType().equals(new Void())) {
                    exitWithMessage(String.format("[Affectation] (%s) can't call a [%s] function",
                                                  functionSymbol.getIdent(), functionSymbol.getReturnType().getType()));
                }
            }

            if (symbol instanceof PrototypeSymbol) {
                PrototypeSymbol prototypeSymbol = (PrototypeSymbol) symbol;

                if (prototypeSymbol.getReturnType().equals(new Void())) {
                    exitWithMessage(String.format("[Affectation] (%s) can't call a [%s] function",
                                                  prototypeSymbol.getIdent(),
                                                  prototypeSymbol.getReturnType().getType()));
                }
            }
        }
    }

    @Override
    public String pp() {
        checkError();

        return this.leftVar.pp() + " := " + this.expression.pp();
    }

    @Override
    public GenericRet toIR() throws TypeException {
        checkError();

        GenericRet result = new GenericRet();

        GenericRet rightResult = expression.toIR();
        result.getIr().appendAll(rightResult.getIr());

        String leftVarIdent = "";

        if (leftVar instanceof Array) {
            Symbol leftSymbol = this.symbolTable.lookup(leftVar.getIdent());
            
            if (leftSymbol instanceof VariableSymbol)
            {
                VariableSymbol variableSymbol = (VariableSymbol)leftSymbol;
                
                String resultIdent = Utils.newtmp();
                GenericRet leftRet = leftVar.toIR();

                if (leftRet.getIr().isEmpty()) {
                    result.getIr().appendAll(leftRet.getIr());
                    result.getIr().appendCode(
                            new LoadTab(resultIdent,
                                        "%" + leftVar.getIdent(),
                                        leftRet.getResult(),
                                        variableSymbol.getSize()));
                }

                leftVarIdent = resultIdent;
            }
        } else {
            leftVarIdent = "%" + leftVar.getIdent(); // Add index
        }

        result.getIr().appendCode(new Store(new LlvmInt(), leftVarIdent, rightResult.getResult()));

        return result;
    }
}