package TP2.ASD;

import TP2.ASD.Ret.GenericRet;

public interface VariableFormDeclarationInterface
{
    String getIdent();
    
    String pp();

    GenericRet toIR();
}
