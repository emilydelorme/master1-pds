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
    public void checkError()
    {
        for (ItemInterface itemInterface : this.items)
        {
            itemInterface.checkError();
        }
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder str = new StringBuilder("PRINT ");
        
        int itemsSize = this.items.size();
        
        for(int i = 0; i < itemsSize; ++i)
        {
            if (this.items.get(i) instanceof TP2.ASD.Item.Expression)
            {
                str.append(this.items.get(i).pp());
            }
            
            if (this.items.get(i) instanceof TP2.ASD.Item.Text)
            {
                str.append("\"").append(this.items.get(i).pp()).append("\"");
            }
            
            if (i < itemsSize - 1)
            {
                str.append(", ");
            }
        }
        
        return str.toString();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        return null;
    }
}
