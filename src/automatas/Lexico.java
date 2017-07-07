/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import ListaDinamica.Lista_desordenada;
import ListaDinamica.TDAToken;
import java.util.ArrayList;

/**
 *
 * @author TOSHIBA
 */
public class Lexico {
    int cont=0;
    public ArrayList<String> erroresLexico;
    public Lista_desordenada tablaSimbolos;
    public ArrayList<String> palabrasReservadas;
    public ArrayList<String> operadoresRelacionales;
    public ArrayList<String> operadoresAritmeticos;
    public String operadorAsignacion;
    
    public Lexico() {
        //inicializar las estructuras de datos
        erroresLexico = new ArrayList();
        tablaSimbolos = new Lista_desordenada();
        palabrasReservadas = new ArrayList();
        operadoresRelacionales  = new ArrayList();
        operadoresAritmeticos = new ArrayList();
        
        //ingresar las palabras reservadas al arreglo
        palabrasReservadas.add("Ice");
        palabrasReservadas.add("Fire");
        palabrasReservadas.add("Dragon");
        palabrasReservadas.add("Groat");
        palabrasReservadas.add("HalfGroat");
        palabrasReservadas.add("Moon");
        palabrasReservadas.add("Stag");
        
        //ingresar los operadores relacionales
        operadoresRelacionales.add(">");
        operadoresRelacionales.add("<");
        operadoresRelacionales.add("==");
        operadoresRelacionales.add(">=");
        operadoresRelacionales.add("<=");
        operadoresRelacionales.add("!=");
        
        //ingresar operadores aritmeticos
        operadoresAritmeticos.add("+");
        operadoresAritmeticos.add("-");
        operadoresAritmeticos.add("*");
        operadoresAritmeticos.add("/");
        operadoresAritmeticos.add("%");
        
        //definir el operador de asignacion
        operadorAsignacion = "=";
    }
    
    public String clasificar(String token) {
       
        //Comparar si el token es igual al operador de asignacion
        if (operadorAsignacion.equals(token)) {
            //actualmente el operador de asignacion no se ingresa en la tabla de simbolos
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 5;
            tablaSimbolos.Insertar(registro);
            System.out.println("Es el operador de asignacion");
            return "OPA";
        }
        
        //Comparar si el token esta en la tabla de palabras reservadas
        if (palabrasReservadas.contains(token)) {
            
            cont++;
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 1;
            tablaSimbolos.Insertar(registro);
            System.out.println("Es una palabra reservada");
            return "PR"+cont;
        }
        
        //Comparar si el token esta en la tabla de operadores aritmeticos
        if (operadoresAritmeticos.contains(token)) {
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 3;
            tablaSimbolos.Insertar(registro);
            System.out.println("Es un operador aritmetico");
            return "OPAR";
        }
        
        //Comparar si el token esta en la tabla de operadores relacionales
        if (operadoresRelacionales.contains(token)) {
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 4;
            tablaSimbolos.Insertar(registro);
            System.out.println("Es un operador relacional");
            return "OPR";
        }
        
        //Ingresar el token al automata de numeros
        String resultadoNumero = automataNumeros(token);
        if (!resultadoNumero.equals("nan")) {
            //actualmente no se ingresan los numeros a la tabla de simbolos
            System.out.println("Es un numero "+resultadoNumero);
            return "NUM";
        }
        
        //Ingresar el token al automata de identificadores
        if (automataIdentificadores(token)) {
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 2;
            tablaSimbolos.Insertar(registro);
            System.out.println("Es un identificador");
            return "IDEN";
        }
        
        //ingresar el token en el automata de cadenas
        if (automataCadenas(token)) {
            //actualmente no se ingresan las cadenas a la tabla de simbolos
            System.out.println("Es una cadena");
            return "CAD";
        }
        
        //ingresar el token en el automata de comentarios
        if (automataComentarios(token)) {
            //actualmente no se ingresan las cadenas a la tabla de simbolos
            System.out.println("Es un comentario");
            return "COMEN";
        }
        
        //Si llego hasta este punto significa que el toekn no cayo en ninguna de las clasificaciones y hay que ingresarlo a los errores
        System.out.println("Es un error lexico");
        erroresLexico.add(token);
        return "ERROR";
        
    }
    
    /*
    * Devuelve una cadena que indica si el numero es entero, real o ninguno
    *
    */
    public String automataNumeros(String token) {
        
        int posicion = 0;
        String estadoActual = "inicio";
        boolean cadenaRechazada = false;
        
        while (!cadenaRechazada && posicion < token.length()) {
            
            char simbolo = token.charAt(posicion);
            
            switch (estadoActual) {
                
                case "inicio":
                    if (Character.isDigit(simbolo)) {
                        estadoActual = "entero";
                    } else if (simbolo == '+' || simbolo == '-') {
                        estadoActual = "q1";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "q1":
                    if (Character.isDigit(simbolo)) {
                        estadoActual = "entero";
                    } else if (simbolo == '.') {
                        estadoActual = "q2";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                
                case "q2":
                    if (Character.isDigit(simbolo)) {
                        estadoActual = "real";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "entero":
                    if (Character.isDigit(simbolo)) {
                        estadoActual = "entero";
                    } else if (simbolo == '.') {
                        estadoActual = "q2";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "real":
                    if (Character.isDigit(simbolo)) {
                        estadoActual = "real";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
            }
            
            posicion++;
            
        }
        
        if (cadenaRechazada)
            return "nan";
        
        return estadoActual;
        
    }
    
    /*
    * Devuelve true si la cadena recibida es un identificador
    * Devuelve false si la cadena recibida no cumple con las condiciones de un identificador
    */
    public boolean automataIdentificadores(String token) {
        
        int posicion = 0;
        String estadoActual = "inicio";
        boolean cadenaRechazada = false;
        
        while (!cadenaRechazada && posicion < token.length()) {
            
            char simbolo = token.charAt(posicion);
            
            switch (estadoActual) {
                
                case "inicio":
                    if (Character.isAlphabetic(simbolo)) {
                        estadoActual = "q1";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "q1":
                    if (Character.isDigit(simbolo) || Character.isAlphabetic(simbolo) || simbolo == '_') {
                        estadoActual = "q1";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                
            }
            
            posicion++;
            
        }
        
        if (cadenaRechazada)
            return false;
        else
            return true;
        
    }
    
    
    /*
    * Automata que recibe una cadena e indica si se trata de una cadena encerrada en comillas dobles
    * Si se trata de una cadena devuelve true
    * Si no es una cadena devuelve false
    */
    public boolean automataCadenas(String token) {

        int posicion = 0;
        String estadoActual = "inicio";
        boolean cadenaRechazada = false;
        
        while (!cadenaRechazada && posicion < token.length()) {
            
            char simbolo = token.charAt(posicion);
            
            switch (estadoActual) {
                
                case "inicio":
                    if (simbolo == '"') {
                        estadoActual = "q1";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "q1":
                    if (simbolo == '"') {
                        estadoActual = "q2";
                    } else {
                        //sigue en el estado q1
                    }
                    break;
                    
                case "q2":
                    //cualquier caracter despues de las comillas dobles hace que no sea una cadena valida
                    cadenaRechazada = true;
                    break;
                
            }
            
            posicion++;
            
        }
        
        if (cadenaRechazada)
            return false;
        else if (estadoActual.equals("q2"))
            return true;
        else
            return false;

    }
    
    
    /*
    * Automata que recibe una cadena e indica si se trata de un comentario encerrado entre >*esto es un comentario*<
    * Si se trata de un comentario devuelve true
    * Si no es un comentario devuelve false
    */
    public boolean automataComentarios(String token) {

        int posicion = 0;
        String estadoActual = "inicio";
        boolean cadenaRechazada = false;
        
        while (!cadenaRechazada && posicion < token.length()) {
            
            char simbolo = token.charAt(posicion);
            
            switch (estadoActual) {
                
                case "inicio":
                    if (simbolo == '>') {
                        estadoActual = "q1";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "q1":
                    if (simbolo == '*') {
                        estadoActual = "q2";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "q2":
                    if (simbolo == '*') {
                        estadoActual = "q3";
                    } else {
                        //cualquier simbolo te mantiene en el estado actual
                    }
                    break;
                    
                case "q3":
                    if (simbolo == '<') {
                        estadoActual = "q4";
                    } else {
                        estadoActual = "q2";
                    }
                    break;
                
                case "q4":
                    //cualquier caracter despues de cerrar el *< hace que no sea un comentario valido
                    cadenaRechazada = true;
                    break;
                
            }
            
            posicion++;
            
        }
        
        if (cadenaRechazada)
            return false;
        else if (estadoActual.equals("q4"))
            return true;
        else
            return false;

    }
    
    
}
