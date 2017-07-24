/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Frank
 */
public class Utils {
    public Utils() 
    {
        
    }
    
    public static boolean esDigito(char simbolo) {
        
        if (simbolo == '0' || simbolo == '1' || simbolo == '2' || simbolo == '3' || simbolo == '4' || simbolo == '5' || simbolo == '6' || simbolo == '7' || simbolo == '8' || simbolo == '9')
            return true;
        else
            return false;
        
    }
    
    
    public static boolean esLetra(char simbolo) {
        
        if (simbolo == 'a' || simbolo == 'b' || simbolo == 'c' || simbolo == 'd' || simbolo == 'e' || 
                simbolo == 'f' || simbolo == 'g' || simbolo == 'h' || simbolo == 'i' || simbolo == 'j' || 
                simbolo == 'k' || simbolo == 'l' || simbolo == 'm' || simbolo == 'n' || simbolo == 'ñ' || 
                simbolo == 'o' || simbolo == 'p' || simbolo == 'q' || simbolo == 'r' || simbolo == 's' || 
                simbolo == 't' || simbolo == 'u' || simbolo == 'v' || simbolo == 'w' || simbolo == 'x' || 
                simbolo == 'y' || simbolo == 'z' || 
                simbolo == 'A' || simbolo == 'B' || simbolo == 'C' || simbolo == 'D' || simbolo == 'E' || 
                simbolo == 'F' || simbolo == 'G' || simbolo == 'H' || simbolo == 'I' || simbolo == 'J' || 
                simbolo == 'K' || simbolo == 'L' || simbolo == 'M' || simbolo == 'N' || simbolo == 'Ñ' || 
                simbolo == 'O' || simbolo == 'P' || simbolo == 'Q' || simbolo == 'R' || simbolo == 'S' || 
                simbolo == 'T' || simbolo == 'U' || simbolo == 'V' || simbolo == 'W' || simbolo == 'X' || 
                simbolo == 'Y' || simbolo == 'Z' ||
                simbolo == 'á' || simbolo == 'é' || simbolo == 'í' || simbolo == 'ó' || simbolo == 'ú' ||
                 simbolo == 'Á' || simbolo == 'É' || simbolo == 'Í' || simbolo == 'Ó' || simbolo == 'Ú')
            return true;
        else
            return false;
        
    }
  
    public void separaTokens(String cadena,ArrayList tokens)
    {
       String delimitadores= "[ ]";
      if(!cadena.isEmpty())
        {
        String[] palabras =cadena.split(delimitadores);
        for (int i = 0; i < palabras.length ; i++) {
            //System.out.println(palabras[i]+"");
        }
        Boolean banS=false,banC=false;
        String strC="",strS="";
        int conS=0;
            for (int i = 0; i < palabras.length; i++) 
            {
                Boolean banO=false;
                if(palabras[i].contains(">*")&&banS==false)
                    banC=true;
                
                if(palabras[i].contains("*<")&&banS==false)
                {
                    strC+=palabras[i];
                    banC=false;
                    banO=true;
                    tokens.add(strC);
                    strC="";
                }
                if(palabras[i].contains("\"")&&banC==false)
                { 
                    banS=true;
                    conS++;
                    int con=0;
                    Boolean veri=verificaComilas(palabras[i]);
                    if(veri)
                    {
                            banS=false;
                            banO=true;
                            tokens.add(palabras[i]);
                            strS="";
                            conS=0;
                    }
                    
                }
                if(palabras[i].contains("\"")&&banC==false&&conS==2)
                {
                    strS+=palabras[i];
                    banS=false;
                    banO=true;
                    tokens.add(strS);
                    strS="";
                    conS=0;
                    
                }
               
                if(banC)    
                    strC+=palabras[i]+" ";
                if(banS)   
                    strS+=palabras[i]+" ";
             
                if(banS==false&&banC==false&&banO==false)
                    tokens.add(palabras[i]);
            }
                if(banC)    
                    tokens.add(strC);
                if(banS)   
                    tokens.add(strS);
            
        }else
            {
                System.out.println("cadena vacia");
            }
    }

    public Boolean verificaComilas(String cadena)
    {
        Boolean ban=false;
        String analiza = cadena;
                    int con=0;
                    for (int j = 0; j < analiza.length(); j++)
                    {
                        char letra = analiza.charAt(j);
                        if(letra=='\"') 
                        {
                            con++;          
                        }         
                            if(con==2)
                            {
                                ban=true;
                            }  
                    }
        return ban;
    }
    public String mostrarPila(Stack entrada)
    {   
        String cad="";
        for (int i = 0; i <entrada.size(); i++) 
             {   
                 cad+=entrada.get(i)+" ";   
             }
        cad+="\n";

        return cad;
    }
    public String mostrarEntrada(Stack entrada)
    {   
        String cad="";
        for (int i = 0; i <entrada.size(); i++) 
             {   
                 cad+=entrada.get(i)+" ";   
             }
        cad+="\n";
        return cad;
    }
    public  Boolean buscar(HashMap<String,HashMap> NT,String k1,String k2)
    {
        Boolean ban=false;
        if(NT.containsKey(k1))
        {
            ban=NT.get(k1).containsKey(k2);
        }
        return ban;
    }
    public  String getCruce(HashMap<String,HashMap> NT,String k1,String k2)
    {
        String prod=(String) NT.get(k1).get(k2);
        return prod;
    }
    public  void llenarTablaSintactica(HashMap<String,HashMap> NT)
    {
            HashMap<String,String> T1 = new HashMap<>();
            HashMap<String,String> T2 = new HashMap<>();
            HashMap<String,String> T3 = new HashMap<>();
            HashMap<String,String> T4 = new HashMap<>();
            HashMap<String,String> T5 = new HashMap<>();
            HashMap<String,String> T6 = new HashMap<>();
            HashMap<String,String> T7 = new HashMap<>();
            HashMap<String,String> T8 = new HashMap<>();
            HashMap<String,String> T9 = new HashMap<>();
            HashMap<String,String> T10 = new HashMap<>();
            HashMap<String,String> T11 = new HashMap<>();
            HashMap<String,String> T12 = new HashMap<>();
            HashMap<String,String> T13 = new HashMap<>();
            HashMap<String,String> T14 = new HashMap<>();
            HashMap<String,String> T15 = new HashMap<>();
            HashMap<String,String> T16 = new HashMap<>();
            HashMap<String,String> T17 = new HashMap<>();
            HashMap<String,String> T18 = new HashMap<>();
            HashMap<String,String> T19 = new HashMap<>();
            HashMap<String,String> T20 = new HashMap<>();
          
            NT.put("Prog", T1);
            NT.put("Sentencias", T2);
            NT.put("SentenciasR", T3);
            NT.put("Declaracion", T4);
            NT.put("DeclaracionR", T5);
            NT.put("ID", T6);
            NT.put("IDprima", T7);
            NT.put("IDR", T8);
            NT.put("IDRprima", T9);
            NT.put("Tipo", T10);
            NT.put("Instrucciones", T11);
            NT.put("Instruccion", T12);
            NT.put("Asignacion", T13);
            NT.put("AsignacionCondicional", T14);
            NT.put("InstruccionR", T15);
            NT.put("Condicion", T16);
            NT.put("Expresion", T17);
            NT.put("ExpresionID", T18);
            NT.put("ExpresionNum", T19);
            NT.put("ExpresionR", T20);
            T1.put("Ice","Ice Del Sentencias Fire");
            T2.put("Fire","e");
            T2.put("Dragon","Declaracion SentenciasR");
            T2.put("Moon","Declaracion SentenciasR");
            T2.put("Groat","Declaracion SentenciasR");
            T2.put("HalfGroat","Declaracion SentenciasR");
            T2.put("Stag","Declaracion SentenciasR");
            T3.put("Fire","e");
            T3.put("id","Instrucciones Declaracion SentenciasR");
            T3.put("if","Instrucciones Declaracion SentenciasR");
            T3.put("for","Instrucciones Declaracion SentenciasR");
            T3.put("while","Instrucciones Declaracion SentenciasR");
            T4.put("Fire","e");
            T4.put("id","e");
            T4.put("Dragon","Tipo DeclaracionR");
            T4.put("Moon","Tipo DeclaracionR");
            T4.put("Groat","Tipo DeclaracionR");
            T4.put("HalfGroat","Tipo DeclaracionR");
            T4.put("Stag","Tipo DeclaracionR");
            T4.put("if","e");
            T4.put("for","e");
            T4.put("while","e");
            T5.put("id","ID Del Declaracion");
            T6.put("id","id IDprima");
            T7.put("Del","e");
            T7.put(",","IDR");
            T7.put("Opa","Opa Expresion IDR");
            T8.put("Del","e");
            T8.put(",",", id IDRprima");
            T9.put("Del","e");
            T9.put(",","IDR");
            T9.put("Opa","Opa Expresion IDR");
            T10.put("Dragon","Dragon");
            T10.put("Moon","Moon");
            T10.put("Groat","Groat");
            T10.put("HalfGroat","HalfGroat");
            T10.put("Stag","Stag");
            T11.put("Fire","e");
            T11.put("id","Instruccion Instrucciones");
            T11.put("if","Instruccion Instrucciones");
            T11.put("for","Instruccion Instrucciones");
            T11.put("while","Instruccion Instrucciones");
            T11.put("endelse","e");
            T11.put("endif","e");
            T11.put("endfor","e");
            T11.put("endwhile","e");
            T12.put("id","id Asignacion");
            T12.put("if","if Condicion then Del Instrucciones endif Del InstruccionR");
            T12.put("for","for AsignacionCondicional ; Condicion ; AsignacionCondicional Del Instrucciones endfor Del");
            T12.put("while","while Condicion Del Instrucciones endwhile Del");
            T13.put("Opa","Opa Expresion Del");
            T14.put("id","id Opa Expresion");
            T15.put("else","else Del Instrucciones endelse Del");
            T16.put("id","Expresion oprel Expresion");
            T16.put("num","Expresion oprel Expresion");
            T16.put("cad","Expresion oprel Expresion");
            T17.put("id","id ExpresionID");
            T17.put("num","num ExpresionNum");
            T17.put("cad","cad");
            T18.put("Del","e");
            T18.put(",","e");
            T18.put("then","e");
            T18.put(";","e");
            T18.put("oprel","e");
            T18.put("opari","ExpresionR");
            T19.put("Del","e");
            T19.put(",","e");
            T19.put("then","e");
            T19.put(";","e");
            T19.put("oprel","e");
            T19.put("opari","ExpresionR");
            T20.put("Del","e");
            T20.put("opari","opari Expresion ExpresionR");
            T20.put("oprel","e");
            T20.put("then","e");
            /*
            T.put("Ice","");
            T.put("Fire","");
            T.put("Del","");
            T.put(",","");
            T.put("id","");
            T.put("Dragon","");
            T.put("Moon","");
            T.put("Groat","");
            T.put("HalfGroat","");
            T.put("Stag","");
            T.put("Opa","");
            T.put("if","");
            T.put("then","");
            T.put("endif","");
            T.put("for","");
            T.put(";","");
            T.put("endfor","");
            T.put("while","");
            T.put("endwhile","");
            T.put("oprel","");
            T.put("num","");
            T.put("cad","");
            T.put("opari","");
            T.put("else","");
            T.put("endelse","");
            T.put("$","");*/
    }
         
   
    
}
