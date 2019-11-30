package TP2.Llvm.Instructions.Operations;

import TP2.Llvm.Instruction;

public class GenericOperation implements Instruction
{
    private Operation operation;
    private String left;
    private String right;
    private String lvalue;

    public GenericOperation(Operation operation, String left, String right, String lvalue)
    {
        this.operation = operation;
        this.left = left;
        this.right = right;
        this.lvalue = lvalue;
    }


    @Override
    public String toString()
    {
        return lvalue + " = add " + operation.toString().toLowerCase() + " " + left + ", " + right + "\n";
    }
}
