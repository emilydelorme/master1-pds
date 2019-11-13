package TP2.ASD;

import TP2.exceptions.TypeException;

public interface ExpressionInterface
{
    String pp();

    Ret toIR() throws TypeException;
}
