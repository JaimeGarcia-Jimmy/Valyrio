/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import java.util.ArrayList;

/**
 *
 * @author TOSHIBA
 */
public class NodoArbol {
    ArrayList<String> info;
    NodoArbol izq;
    NodoArbol der;
    NodoArbol padre;
    
    public NodoArbol(ArrayList<String> I){
        info=I;
        izq=null;
        der=null;
        padre = null;
    }
    
    public NodoArbol(ArrayList<String> I, NodoArbol Iz, NodoArbol Dr){
        info=I;
        izq=Iz;
        der=Dr;
        padre = null;
    }
}
