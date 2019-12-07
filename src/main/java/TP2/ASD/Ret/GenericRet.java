package TP2.ASD.Ret;


import TP2.Llvm.InstructionHandler;

public class GenericRet
{
    private InstructionHandler ir;
    private String result; // The name containing the expression's result
    // (either an identifier, or an immediate value)

    public GenericRet(InstructionHandler ir, String result)
    {
        this.ir = ir;
        this.result = result;
    }

    public GenericRet(String result)
    {
        this(new InstructionHandler(), result);
    }

    public GenericRet()
    {
        this(new InstructionHandler(), "");
    }

    public InstructionHandler getIr()
    {
        return ir;
    }

    public void setIr(InstructionHandler ir)
    {
        this.ir = ir;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

}
