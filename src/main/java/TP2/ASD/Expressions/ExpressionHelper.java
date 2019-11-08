package TP2.ASD.Expressions;

import TP2.ASD.Expression;
import TP2.Llvm;
import TP2.Utils;
import TP2.exceptions.TypeException;

class ExpressionHelper {

    /**
     * Avoid this class to be instanced
     */
    private ExpressionHelper() {
    }

    static Expression.RetExpression retExpression(Expression.RetExpression leftRet, Expression.RetExpression rightRet) throws TypeException {
        // We check if the types mismatches
        if(!leftRet.type.equals(rightRet.type)) {
            throw new TypeException("type mismatch: have " + leftRet.type + " and " + rightRet.type);
        }

        // We base our build on the left generated IR:
        // append right code
        leftRet.ir.append(rightRet.ir);

        // allocate a new identifier for the result
        String result = Utils.newtmp();

        // new sub instruction result = left - right
        Llvm.Instruction sub = new Llvm.Sub(leftRet.type.toLlvmType(), leftRet.result, rightRet.result, result);

        // append this instruction
        leftRet.ir.appendCode(sub);
        return new Expression.RetExpression(leftRet.ir, leftRet.type, result);
    }
}
