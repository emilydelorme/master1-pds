package TP2.ASD.Item;

import TP2.ASD.ItemInterface;
import TP2.ASD.Ret.GenericRet;

public class Text implements ItemInterface
{
    private String value;

    public Text(String value)
    {
        this.value = value;
    }
    
    @Override
    public void checkError()
    {
        // No error check
    }

    @Override
    public String pp()
    {
        checkError();
        return this.value;
    }

    @Override
    public GenericRet toIR()
    {
        checkError();
        return new GenericRet(value != null ? value : "%d");
    }
}
