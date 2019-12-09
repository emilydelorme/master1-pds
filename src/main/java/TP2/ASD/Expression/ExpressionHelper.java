package TP2.ASD.Expression;

import TP2.ASD.Ret.TypeRet;
import TP2.Llvm.Instruction;
import TP2.Llvm.Instructions.Operations.GenericOperation;
import TP2.Llvm.Instructions.Operations.Operation;
import TP2.Utils;
import TP2.exceptions.TypeException;
import org.abego.treelayout.internal.util.java.lang.string.StringUtil;


class ExpressionHelper {

    /**
     * Avoid this class to be instanced
     */
    private ExpressionHelper() {
    }

    static TypeRet retExpression(TypeRet leftRet, TypeRet rightRet, Operation operation) throws TypeException {

        // We check if the types mismatches
        if (!leftRet.getType().equals(rightRet.getType())) {
            throw new TypeException("type mismatch: have " + leftRet.getType() + " and " + rightRet.getType() + "\n"
                                    + leftRet + "\n--------------------------------------------\n" + rightRet);
        }
        TypeRet result = new TypeRet(leftRet.getType());

        result.getIr().appendAll(leftRet.getIr())
              .appendAll(rightRet.getIr());

        // allocate a new identifier for the result
        String resultIdent = Utils.newtmp();
        result.setResult(resultIdent);
        // new sub instruction result = left - right

        String leftRetResult = isNumeric(leftRet.getResult()) ? leftRet.getResult() : "i32 " + leftRet.getResult();
        String rightRetResult = isNumeric(rightRet.getResult()) ? rightRet.getResult() : "i32 " + rightRet.getResult();

        result.getIr().appendCode(new GenericOperation(operation, leftRetResult, rightRetResult, resultIdent));
        return result;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
