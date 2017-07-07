

package ListaDinamica;

import javax.swing.JOptionPane;


public class Menu_Lista {
    public static void main(String[] args) {
        int opcion;
        TDAToken elemento;
        Lista_desordenada lista = new Lista_desordenada();
        
        do{
            opcion = Integer.parseInt(JOptionPane.showInputDialog("1__Insertar\n2__Eliminar\n3__Mostrar\n4__Buscar\n5__Salir"));
            switch(opcion){
                case 1:
                    elemento = new TDAToken();
                    elemento.llave = JOptionPane.showInputDialog("Ingresa llave");
                    elemento.categoria = Integer.parseInt(JOptionPane.showInputDialog("Ingresa categoria"));
                    elemento.tipo = JOptionPane.showInputDialog("Ingresa tipo");
                    elemento.longitud = Integer.parseInt(JOptionPane.showInputDialog("Ingresa longitud"));
                    elemento.valor = JOptionPane.showInputDialog("Ingresa valor");
                    lista.Insertar(elemento);
                    break;
                case 2:
                    elemento = new TDAToken();
                    elemento.llave = JOptionPane.showInputDialog("¿A quién quieres eliminar?");
                    lista.Eliminar(elemento);
                    break;
                case 3:
                    lista.Mostrar();
                    break;
                case 4:
                    elemento = new TDAToken();
                    elemento.llave = JOptionPane.showInputDialog("¿A quién quieres buscar?");
                    Nodo nodoBuscar = lista.buscar(elemento);
                    System.out.println("Llave: "+nodoBuscar.info.llave);
                    System.out.println("Categoria: "+nodoBuscar.info.categoria);
                    System.out.println("Tipo: "+nodoBuscar.info.tipo);
                    System.out.println("Longitud: "+nodoBuscar.info.longitud);
                    System.out.println("Valor: "+nodoBuscar.info.valor);
                    break;
            }
        }while(opcion != 5);
    }
}
