package TP2.ASD.Unit;

import TP2.ASD.ParameterInterface;
import TP2.ASD.Ret.GenericRet;
import TP2.ASD.UnitInterface;

import java.util.List;

import TP2.ASD.TypeInterface;
import TP2.Llvm.Instructions.functions.ProtoFunction;
import TP2.SymbolTable.SymbolTable;
import TP2.exceptions.TypeException;

public class Prototype implements UnitInterface
{
    private TypeInterface type;
    private String ident;
    private List<ParameterInterface> arguments;
    
    public Prototype(TypeInterface type, String ident, List<ParameterInterface> arguments)
    {
        this.type = type;
        this.ident = ident;
        this.arguments = arguments;
    }

    public TypeInterface getType()
    {
        return type;
    }

    public String getIdent()
    {
        return ident;
    }

    public List<ParameterInterface> getArguments()
    {
        return arguments;
    }
    
    @Override
    public void checkError()
    {
        // Only one function prototype allowed, check done in VSLParser.g4 (prototype[TP2.SymbolTable.SymbolTable symbolTable] returns [TP2.ASD.Unit.Prototype out])
    }

    @Override
    public String pp()
    {
        checkError();
        
        StringBuilder strParametres = new StringBuilder();
        
        int parametersSize = this.arguments.size();
        
        for (int i = 0; i < parametersSize; ++i)
        {
            strParametres.append(this.arguments.get(i).pp());
            
            if (i < parametersSize - 1)
            {
                strParametres.append(", ");
            }
        }

        return "PROTO" + " " + this.type.pp() + " " + this.ident + "(" + strParametres + ")" + "";
    }

    @Override
    public GenericRet toIR() throws TypeException
    {
        checkError();
        
        GenericRet result = new GenericRet();

        result.getIr().appendCode(new ProtoFunction(this.ident));

        return result;
    }
}
