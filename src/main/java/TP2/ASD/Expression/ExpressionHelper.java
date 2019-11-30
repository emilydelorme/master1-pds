package TP2.ASD.Expression;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Ret.TypeRet;
import TP2.Llvm.Instruction;
import TP2.Llvm.Instructions.Operations.GenericOperation;
import TP2.Llvm.Instructions.Operations.Operation;
import TP2.Utils;
import TP2.exceptions.TypeException;


class ExpressionHelper
{

    /**
     * Avoid this class to be instanced
     */
    private ExpressionHelper()
    {
    }

    static TypeRet retExpression(TypeRet leftRet, TypeRet rightRet, Operation operation) throws TypeException
    {

        // We check if the types mismatches
        if (!leftRet.getType().equals(rightRet.getType()))
        {
            throw new TypeException("type mismatch: have " + leftRet.getType() + " and " + rightRet.getType());
        }

        // We base our build on the left generated IR:
        // append right code
        leftRet.getIr().appendAll(rightRet.getIr());

        // allocate a new identifier for the result
        String result = Utils.newtmp();

        // new sub instruction result = left - right
        Instruction operationInstruction = new GenericOperation(operation, leftRet.getResult(), rightRet.getResult(), result);

        // append this instruction
        leftRet.getIr().appendCode(operationInstruction);
        return new TypeRet(leftRet.getIr(), result, leftRet.getType());
    }
}
