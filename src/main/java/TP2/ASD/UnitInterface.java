package TP2.ASD;

import TP2.ASD.Ret.GenericRet;
import TP2.exceptions.TypeException;

public interface UnitInterface extends ErrorHandlerInterface
{
    String getIdent();
    
    String pp();
    
    GenericRet toIR() throws TypeException;
}
