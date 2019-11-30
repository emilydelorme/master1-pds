package TP2.ASD;

import TP2.exceptions.TypeException;

public interface VariableFormDeclarationInterface
{
    String pp();

    Ret toIR() throws TypeException;
}
