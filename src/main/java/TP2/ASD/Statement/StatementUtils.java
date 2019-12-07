package TP2.ASD.Statement;

import TP2.ASD.ExpressionInterface;
import TP2.ASD.Item.Expression;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.Llvm.Instructions.Br;
import TP2.Llvm.Instructions.Label;
import TP2.Llvm.Instructions.Operations.CompareToZero;
import TP2.SymbolTable.SymbolTable;
import TP2.Utils;
import TP2.exceptions.TypeException;

public class StatementUtils
{

    private StatementUtils()
    {
    }

    static public GenericRet createControl(ControlType controlType, SymbolTable symbolTable, String startIdent, String endIdent, ExpressionInterface condition, StatementInterface statement) throws TypeException
    {
        return createControl(controlType, symbolTable, startIdent, endIdent, condition, statement, null, null);
    }

    static public GenericRet createControl(ControlType controlType, SymbolTable symbolTable, String startIdent, String endIdent, ExpressionInterface condition, StatementInterface statement, String altIdent, StatementInterface altStatement) throws TypeException
    {
        GenericRet result = new GenericRet();

        if(ControlType.WHILE == controlType) {
            result.getIr().appendCode(new Br(startIdent))
                .appendCode(new Label(startIdent));
        }

        final GenericRet conditionRet = condition.toIR();
        String cond = Utils.newtmp();

        result.getIr().appendAll(conditionRet.getIr())
            .appendCode(new CompareToZero(cond, conditionRet.getResult()))
            .appendCode(new Br(cond,
                ControlType.WHILE == controlType ? altIdent :startIdent,
                ControlType.ELSEIF == controlType ? altIdent : endIdent))
            .appendCode(new Label(startIdent))
            .appendAll(statement.toIR(symbolTable).getIr())
            .appendCode(new Br(ControlType.WHILE == controlType ? startIdent : endIdent));

        if (ControlType.ELSEIF == controlType) {
            result.getIr().appendCode(new Label(altIdent))
                .appendAll(altStatement.toIR(symbolTable).getIr())
                .appendCode(new Br(endIdent));
        }

        result.getIr().appendCode(new Label(endIdent));

        return result;
    }
}
