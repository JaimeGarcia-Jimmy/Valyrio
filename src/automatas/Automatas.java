/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import javax.swing.JOptionPane;

/**
 *
 * @author TOSHIBA
 */
public class Automatas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Lexico objLexico = new Lexico();
        
        while(true) {
            objLexico.clasificar(JOptionPane.showInputDialog("Ingresa token"));
        }
        
    }
    
}
