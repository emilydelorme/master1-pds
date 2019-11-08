package TP2.Unit;

import TP2.ASD.UnitInterface;

import java.util.List;

import TP2.ASD.TypeInterface;;

// prototype(type, IDENT, List<parametre>)
public class Prototype implements UnitInterface
{
    private TypeInterface type;
    private String ident;
    private List<String> parametres;
    
    public Prototype(TypeInterface type, String ident, List<String> parametres)
    {
        this.type = type;
        this.ident = ident;
        this.parametres = parametres;
    }
}
