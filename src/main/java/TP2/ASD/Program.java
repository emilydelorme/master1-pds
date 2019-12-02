package TP2.ASD;

import java.util.List;

import TP2.ErrorHandlerInterface;
import TP2.ASD.Ret.TypeRet;
import TP2.ASD.Types.Void;
import TP2.ASD.Unit.Function;
import TP2.ASD.Unit.Prototype;
import TP2.Llvm.Instruction;
import TP2.Llvm.InstructionHandler;
import TP2.Llvm.Instructions.Return;
import TP2.SymbolTable.SymbolTable;
import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;

public class Program implements ErrorHandlerInterface
{
    private List<UnitInterface> unitInterface;
    private SymbolTable symbolTable;

    public Program(List<UnitInterface> unitInterface, SymbolTable symbolTable)
    {
        this.unitInterface = unitInterface;
        this.symbolTable = symbolTable;
    }
    
    @Override
    public void exitWithMessage(String message)
    {
        System.err.println("ERROR: " + message);
        
        System.exit(1);
    }

    @Override
    public void checkError()
    {
        Function main = null;
        
        boolean duplicatedPrototype = false;
        boolean duplicatedFunction = false;
        
        int unitInterfaceSize = this.unitInterface.size();
        
        for (int i = 0; i < unitInterfaceSize; ++i)
        {
            if (this.unitInterface.get(i) instanceof Function)
            {
                Function tmpFunction = (Function) this.unitInterface.get(i);
                
                if (tmpFunction.getIdent().equals("main"))
                {
                    main = tmpFunction;
                }
                
                for (int j = 0; j < unitInterfaceSize; ++j)
                {
                    if (i == j)
                    {
                        continue;
                    }
                    
                    
                }
            }
            
            if (this.unitInterface.get(i) instanceof Prototype)
            {
                Prototype tmpPrototype = (Prototype) this.unitInterface.get(i);
                
                for (int j = 0; j < unitInterfaceSize; ++j)
                {
                    if (i == j)
                    {
                        continue;
                    }
                    
                    
                }
            }
        }

        if (main == null)
        {
            exitWithMessage("Function main isn't defined.");
            
            return;
        }
        
        if (!main.getParametres().isEmpty())
        {
            exitWithMessage("Function main shouldn't have any parameters.");
        }
        
        // Check duplicated prototype
        
    }

    // Pretty-printer
    public String pp()
    {
        //checkError();
        
        return unitInterface.stream()
                .map(UnitInterface::pp)
                .reduce((e1, e2) -> e1 + "\n" + e2)
                .orElse("");
    }

    // IR generation
    public InstructionHandler toIR() throws TypeException, EmptyProgram
    {
        //checkError();
        
        if (this.unitInterface.isEmpty())
            throw new EmptyProgram("Programme vide");

        TypeRet retExpr = new TypeRet(this.unitInterface.get(0).toIR(), new Void());
        this.unitInterface.remove(0);

        for (UnitInterface unit : this.unitInterface)
        {
            retExpr.getIr().appendAll(unit.toIR().getIr());
        }

        // add a return instruction
        Instruction ret = new Return(retExpr.getType().toLlvmType(), retExpr.getResult());
        retExpr.getIr().appendCode(ret);

        return retExpr.getIr();
    }
}