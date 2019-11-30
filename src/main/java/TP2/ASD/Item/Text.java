package TP2.ASD.Item;

import TP2.ASD.ItemInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.exceptions.TypeException;

public class Text implements ItemInterface
{
    private String text;

    public Text(String text)
    {
        this.text = text;
    }

    @Override
    public String pp()
    {
        return this.text;
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        // TODO Auto-generated method stub
        return null;
    }

}
