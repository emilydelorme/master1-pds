package TP2.ASD;

import TP2.ASD.Ret.GenericRet;

public interface ItemInterface extends ErrorHandlerInterface
{
    String pp();

    GenericRet toIR();
}
