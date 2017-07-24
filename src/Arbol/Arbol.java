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
public class Arbol {
    NodoArbol raiz;
    NodoArbol nodoActual;
    
    public Arbol() {
        raiz = null;
        nodoActual = null;
    }
    
    
    public void insertar(ArrayList<String> arrExpresion) {
        if (raiz == null) {
            //caso en el que la expresion es unicamente un numero o un id
            if (arrExpresion.size() == 1) {
                //agregar solo un nodo al arbol con el tipo del numero o del id en info
                raiz = new NodoArbol(arrExpresion);
            }
            else {
                ArrayList<String> newArrExpresion = new ArrayList();
                //asignar signo de operador a newArrExpresion
                newArrExpresion.add(arrExpresion.get(1));
                //insertar operarador en la raiz
                raiz = new NodoArbol(newArrExpresion);
                //asignar el primer numero o id a la izquierda del operador
                newArrExpresion.set(0, arrExpresion.get(0));
                raiz.izq = new NodoArbol(newArrExpresion);
                //el resto de la expresion se asigna a la derecha del nodo al llamar a insertar
                //guardar el resto de la expresion en un nuevo arreglo
                ArrayList<String> restoExpresion = new ArrayList<String>(arrExpresion.subList(2, arrExpresion.size()));
                //mover el nodoActual
                nodoActual = raiz;
                //insertar el resto de la expresion a la derecha del nodo raiz
                insertar(restoExpresion);
            }
            
        }
        else {
            //si ya es el ultimo termino de la expresion
            if (arrExpresion.size() == 1){
                //insertar a la derecha del nodo actual
                nodoActual.der = new NodoArbol(arrExpresion);
                //asignar padre al nodo recien insertado
                nodoActual.der.padre = nodoActual;
            }
            else {
                ArrayList<String> newArrExpresion = new ArrayList();
                //asignar signo de operador a newArrExpresion
                newArrExpresion.add(arrExpresion.get(1));
                //insertar operarador a la derecha del nodo actual
                nodoActual.der = new NodoArbol(newArrExpresion);
                //asignar padre al nodo recien insertado
                nodoActual.der.padre = nodoActual;
                //asignar el primer numero o id a la izquierda del nodo creado en el paso anterior
                newArrExpresion.set(0, arrExpresion.get(0));
                nodoActual.der.izq = new NodoArbol(newArrExpresion);
                //el resto de la expresion se asigna a la derecha del nodo al llamar a insertar
                //guardar el resto de la expresion en un nuevo arreglo
                ArrayList<String> restoExpresion = new ArrayList<String>(arrExpresion.subList(2, arrExpresion.size()));
                //mover el nodoActual
                nodoActual = nodoActual.der;
                //insertar el resto de la expresion a la derecha del nodo actual
                insertar(restoExpresion);
            }
        }
    }
    
    /*
     * Devuelve en un string el tipo del arbol previamente construido
    */
    public String determinarTipo() {
        String tipo = "";
        //caso en el que la expresion es solo un numero o un id, en este caso el nodo raiz tendria null en su nodo izquierdo
        if (raiz.izq == null) {
            tipo = raiz.info.get(0);
            if (tipo.equals("Dragon") || tipo.equals("cad"))
                return "cad";
            else if(tipo.equals("Groat") || tipo.equals("HalfGroat") || tipo.equals("real"))
                return "real";
            else if(tipo.equals("Moon") || tipo.equals("entero"))
                return "entero";
            else
                return "errorRaiz";
        }
        
        //despues de llenar el arbol con insertar, el nodoActual se encuentra en el padre de los ultimos dos nodos hoja, la ultima operacion
        while (nodoActual != null && !tipo.equals("error")) {
            //comparar los tipos de los nodos hijos
            tipo = reglasTipos(nodoActual.izq.info.get(0), nodoActual.der.info.get(0));
            //asignar el resultado al nodo actual
            nodoActual.info.set(0, tipo);
            //moverse al nodo padre
            nodoActual = nodoActual.padre;
        }
        
        return tipo;
    }
    
    /*
     * Contiene las reglas que se aplican al comparar dos tipos de datos en una operacin aritmetica
    */
    public String reglasTipos(String tipo1, String tipo2) {
        //si cualquiera de los tipos es cadena devolver error
        if (tipo1.equals("cad") || tipo2.equals("cad") || tipo1.equals("Dragon") || tipo2.equals("Dragon"))
            return "error";
        else if(tipo1.equals("real") || tipo2.equals("real") || tipo1.equals("Groat") || tipo2.equals("Groat") || tipo1.equals("HalfGroat") || tipo2.equals("HalfGroat")) {
            //si cualquiera de los operandos es real el resultado es un real
            return "real";
        }
        else {
            //si no es cadena ni es real, entonces los dos operandos son enteros
            return "entero";
        }
    }
    
}
