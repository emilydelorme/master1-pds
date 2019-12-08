package TP2.ASD.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import TP2.ASD.Item.Expression;
import TP2.ASD.Item.Text;
import TP2.ASD.ItemInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.StatementInterface;
import TP2.Llvm.Instruction;
import TP2.Llvm.Instructions.io.PrintCall;
import TP2.Llvm.Instructions.io.PrintHeader;
import TP2.TypeLabel;
import TP2.Utils;
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
        for (ItemInterface itemInterface : this.items) {
            itemInterface.checkError();
        }
    }

    @Override
    public String pp()
    {
        checkError();
        StringBuilder str = new StringBuilder("PRINT ");

        IntStream.range(0, this.items.size()).forEach(i -> {
            if (this.items.get(i) instanceof Expression) {
                str.append(this.items.get(i).pp());
            }
            if (this.items.get(i) instanceof Text) {
                str.append("\"").append(this.items.get(i).pp()).append("\"");
            }
            if (i < this.items.size() - 1) {
                str.append(", ");
            }
        });
        return str.toString();
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        GenericRet result = new GenericRet();
        String printIdent = Utils.newLabel(TypeLabel.FMT);

        // Text
        final PrintHeader printHeader = getHeader(printIdent);
        result.getIr().appendHeader(printHeader);

        // Expressions
        result.getIr().appendCode(getCode(printIdent, printHeader));

        return result;
    }

    private PrintHeader getHeader(String printIdent) {
        Utils.LLVMStringConstant text = Utils.stringTransform(
                items.stream()
                        .filter(item -> item instanceof TP2.ASD.Item.Text)
                        .map(ItemInterface::toIR)
                        .map(GenericRet::getResult)
                        .reduce((result1, result2) -> result1 + "" + result2).orElse(""));

        return new PrintHeader(printIdent, text);
    }

    private Instruction getCode(String printIdent, PrintHeader printHeader) {
        List<String> vars = new ArrayList<>();
        GenericRet result = new GenericRet();

        items.stream()
                .filter(item -> item instanceof TP2.ASD.Item.Expression)
                .forEach(expression -> {
                    final GenericRet ret = expression.toIR();
                    vars.add(ret.getResult());
                    result.getIr().appendAll(ret.getIr());
                });

        return new PrintCall(printIdent, printHeader.getLenght(), vars);
    }

}
