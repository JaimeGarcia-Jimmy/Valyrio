/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import ListaDinamica.Lista_desordenada;
import ListaDinamica.Nodo;
import ListaDinamica.TDAToken;
import compilador.Utils;
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
        palabrasReservadas.add("for");
        palabrasReservadas.add(";");
        palabrasReservadas.add("endfor");
        palabrasReservadas.add("if");
        palabrasReservadas.add("then");
        palabrasReservadas.add("endif");
        palabrasReservadas.add("else");
        palabrasReservadas.add("endelse");
        palabrasReservadas.add(",");
        
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
            //System.out.println("Es el operador de asignacion");
            return "Opa";
        }
        
        //Comparar si el token esta en la tabla de palabras reservadas
        if (palabrasReservadas.contains(token)) {
            
            cont++;
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 1;
            tablaSimbolos.Insertar(registro);
            //System.out.println("Es una palabra reservada");
            //return "PR"+cont;
            return token;
        }
        
        //Comparar si el token esta en la tabla de operadores aritmeticos
        if (operadoresAritmeticos.contains(token)) {
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 3;
            tablaSimbolos.Insertar(registro);
            //System.out.println("Es un operador aritmetico");
            return "opari";
        }
        
        //Comparar si el token esta en la tabla de operadores relacionales
        if (operadoresRelacionales.contains(token)) {
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 4;
            tablaSimbolos.Insertar(registro);
            //System.out.println("Es un operador relacional");
            return "oprel";
        }
        
        //Ingresar el token al automata de numeros
        String resultadoNumero = automataNumeros(token);
        if (!resultadoNumero.equals("nan")) {
            //actualmente no se ingresan los numeros a la tabla de simbolos
            //System.out.println("Es un numero "+resultadoNumero);
            return "num";
        }
        
        //Ingresar el token al automata de identificadores
        if (automataIdentificadores(token)) {
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 2;
            tablaSimbolos.Insertar(registro);
           // System.out.println("Es un identificador");
            return "id";
        }
        
        //ingresar el token en el automata de cadenas
        if (automataCadenas(token)) {
            //actualmente no se ingresan las cadenas a la tabla de simbolos
            //System.out.println("Es una cadena");
            return "cad";
        }
        
        //ingresar el token en el automata de comentarios
        if (automataComentarios(token)) {
            //actualmente no se ingresan las cadenas a la tabla de simbolos
            //System.out.println("Es un comentario");
            return "COMEN";
        }
        
        //Si llego hasta este punto significa que el toekn no cayo en ninguna de las clasificaciones y hay que ingresarlo a los errores
       // System.out.println("Es un error lexico");
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
                    if (Utils.esDigito(simbolo)) {
                        estadoActual = "entero";
                    } else if (simbolo == '+' || simbolo == '-') {
                        estadoActual = "q1";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "q1":
                    if (Utils.esDigito(simbolo)) {
                        estadoActual = "entero";
                    } else if (simbolo == '.') {
                        estadoActual = "q2";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                
                case "q2":
                    if (Utils.esDigito(simbolo)) {
                        estadoActual = "real";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "entero":
                    if (Utils.esDigito(simbolo)) {
                        estadoActual = "entero";
                    } else if (simbolo == '.') {
                        estadoActual = "q2";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "real":
                    if (Utils.esDigito(simbolo)) {
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
                    if (Utils.esLetra(simbolo)) {
                        estadoActual = "q1";
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                    
                case "q1":
                    if (Utils.esDigito(simbolo) || Utils.esLetra(simbolo) || simbolo == '_') {
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
    
    /*
     * Recibe una linea de declaracion del codigo fuente y asigna el tipo a los
     * identificadores en la tabla de simbolos
     */
    public void examinarDeclaracion(String declaracion) {
        System.out.println("*************************************");
        System.out.println("Examinar Declaracion");
        System.out.println("*************************************");
        //TDAtoken para la busqueda
        TDAToken tokenIden = new TDAToken();
        //Nodo para guardar la posicion del identificador buscado en la tabla de simbolos
        Nodo nodoIdentificador;
        
        //dividir la declaracion en palabras
        String[] arrDeclaracion = declaracion.split(" ");
        //el primer elemento siempre es el tipo
        String tipo = arrDeclaracion[0];
        //recorrer las palabras de la declaracion y procesar solo los identificadores
        //empieza en el elemento 1 porque el 0 fue el tipo
        for (int i=1 ; i<arrDeclaracion.length ; i++) {
            
            //revisar que la palabra no sea reservada y revisar si la palabra es identificador con el automata
            if (!palabrasReservadas.contains(arrDeclaracion[i]) && automataIdentificadores(arrDeclaracion[i])) {
                //modificar el campo llave de tokenIden para la busqueda
                tokenIden.llave = arrDeclaracion[i];
                //buscar el identificador en la tabla de simbolos y asignarle su tipo
                nodoIdentificador = tablaSimbolos.buscar(tokenIden);
                nodoIdentificador.info.tipo = tipo;
                System.out.println("Palabra "+i+": "+ arrDeclaracion[i] +" es identificador de tipo "+tipo);
            }
            else {
                System.out.println("Palabra "+i+": "+ arrDeclaracion[i] +" no es identificador");
            }
        }
        
    }
    
    //metodo alterno que regresa tipos de numeros y automat char(pendiente) 
    public String clasificar2(String token) {
       
        //Comparar si el token es igual al operador de asignacion
        if (operadorAsignacion.equals(token)) {
            //actualmente el operador de asignacion no se ingresa en la tabla de simbolos
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 5;
            tablaSimbolos.Insertar(registro);
            //System.out.println("Es el operador de asignacion");
            return "Opa";
        }
        
        //Comparar si el token esta en la tabla de palabras reservadas
        if (palabrasReservadas.contains(token)) {
            
            cont++;
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 1;
            tablaSimbolos.Insertar(registro);
            //System.out.println("Es una palabra reservada");
            //return "PR"+cont;
            return token;
        }
        
        //Comparar si el token esta en la tabla de operadores aritmeticos
        if (operadoresAritmeticos.contains(token)) {
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 3;
            tablaSimbolos.Insertar(registro);
            //System.out.println("Es un operador aritmetico");
            return "opari";
        }
        
        //Comparar si el token esta en la tabla de operadores relacionales
        if (operadoresRelacionales.contains(token)) {
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 4;
            tablaSimbolos.Insertar(registro);
            //System.out.println("Es un operador relacional");
            return "oprel";
        }
        
        //Ingresar el token al automata de numeros
        String resultadoNumero = automataNumeros(token);
        if (!resultadoNumero.equals("nan")) {
            //actualmente no se ingresan los numeros a la tabla de simbolos
            //System.out.println("Es un numero "+resultadoNumero);
            return resultadoNumero;
        }
        
        //Ingresar el token al automata de identificadores
        if (automataIdentificadores(token)) {
            TDAToken registro = new TDAToken();
            registro.llave = token;
            registro.categoria = 2;
            tablaSimbolos.Insertar(registro);
           // System.out.println("Es un identificador");
            return "id";
        }
        
        //ingresar el token en el automata de cadenas
        if (automataCadenas(token)) {
            //actualmente no se ingresan las cadenas a la tabla de simbolos
            //System.out.println("Es una cadena");
            return "cad";
        }
        
        //ingresar el token en el automata de comentarios
        if (automataComentarios(token)) {
            //actualmente no se ingresan las cadenas a la tabla de simbolos
            //System.out.println("Es un comentario");
            return "COMEN";
        }
        
        //Si llego hasta este punto significa que el toekn no cayo en ninguna de las clasificaciones y hay que ingresarlo a los errores
       // System.out.println("Es un error lexico");
        erroresLexico.add(token);
        return "ERROR";
        
    }
    
    
    
    
}
