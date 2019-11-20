package TP2.ASD;

import TP2.exceptions.TypeException;

public interface VariableFormeInterface
{
    String pp();

    Ret toIR() throws TypeException;
}
