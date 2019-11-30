package TP2.ASD.Item;

import TP2.ASD.ItemInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.exceptions.TypeException;

public class Text implements ItemInterface
{
    private String value;

    public Text(String value)
    {
        this.value = value;
    }

    @Override
    public String pp()
    {
        return this.value;
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }

}
