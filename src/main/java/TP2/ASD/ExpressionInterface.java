package TP2.ASD;

import TP2.ASD.Ret.GenericRet;
import TP2.ASD.Ret.TypeRet;
import TP2.exceptions.TypeException;

public interface ExpressionInterface
{
    String pp();

    TypeRet toIR() throws TypeException;
}
