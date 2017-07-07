/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public class Utils {
    public Utils() 
    {
        
    }
  
    public void separaTokens(String cadena,ArrayList tokens)
    {
       String delimitadores= "[ ;]";
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
         
   
    
}
