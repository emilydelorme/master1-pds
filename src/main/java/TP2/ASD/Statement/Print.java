package TP2.ASD.Statement;

import java.util.List;

import TP2.ASD.ItemInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.exceptions.TypeException;

public class Print implements StatementInterface
{
    private List<ItemInterface> items;

    public Print(List<ItemInterface> items)
    {
        this.items = items;
    }

    @Override
    public String pp()
    {
        String str = "PRINT ";
        
        int itemsSize = this.items.size();
        
        for(int i = 0; i < itemsSize; ++i)
        {
            if (this.items.get(i) instanceof TP2.ASD.Item.Expression)
            {
                str += this.items.get(i).pp();
            }
            
            if (this.items.get(i) instanceof TP2.ASD.Item.Text)
            {
                str += "\"" + this.items.get(i).pp() + "\"";
            }
            
            if (i < itemsSize - 1)
            {
                str += ", ";
            }
        }
        
        return str;
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        return null;
    }

}
