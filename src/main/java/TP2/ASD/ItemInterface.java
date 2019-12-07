package TP2.ASD;

import TP2.ASD.Ret.GenericRet;
import TP2.exceptions.TypeException;

public interface ItemInterface extends ErrorHandlerInterface
{
    String pp();

    GenericRet toIR();
}
