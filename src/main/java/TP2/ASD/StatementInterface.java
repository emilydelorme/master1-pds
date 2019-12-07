package TP2.ASD;

import TP2.ASD.Ret.GenericRet;
import TP2.SymbolTable.Symbol;
import TP2.SymbolTable.SymbolTable;
import TP2.exceptions.TypeException;

public interface StatementInterface extends ErrorHandlerInterface
{
    String pp();
    GenericRet toIR(SymbolTable symbolTable) throws TypeException;
}
