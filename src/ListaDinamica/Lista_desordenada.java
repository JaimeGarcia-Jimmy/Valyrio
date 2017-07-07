

package ListaDinamica;

import javax.swing.table.DefaultTableModel;


public class Lista_desordenada {
    Nodo Inicio;
    
    public Lista_desordenada(){
        Inicio=null;
    }
    
    public void Insertar(TDAToken valor){
        //verificar si el token ya esta en la lista
        Nodo buscado = buscar(valor);
        
        if(buscado.info.categoria == 0)
            Inicio=new Nodo(valor, Inicio);
    }
    
    public void Mostrar(){
        Nodo recorre=Inicio;
        while(recorre!=null){
            System.out.println("Llave: "+recorre.info.llave);
            System.out.println("Categoria: "+recorre.info.categoria);
            System.out.println("Longitud: "+recorre.info.longitud);
            System.out.println("Valor: "+recorre.info.valor);
            recorre=recorre.siguiente;
        }
    }
     public void llenarTabla(DefaultTableModel tabla){
        Nodo recorre=Inicio;
        Object [] fila = new Object[5];
        while(recorre!=null){
            fila[0]=recorre.info.llave;
            fila[1]=recorre.info.categoria;
            fila[2]=recorre.info.tipo;
            fila[3]=recorre.info.longitud;
            fila[4]=recorre.info.valor;
            tabla.addRow(fila);
            
            recorre=recorre.siguiente;
        }
    }
    public void limpiarTabla(DefaultTableModel tabla){
        int cont =tabla.getRowCount();
       for (int i = 0; i < cont ; i++) 
       {
           tabla.removeRow(0);
       }
   }
     
    public void Eliminar(TDAToken valorE){
        Nodo recorre, anterior;
        
        if(Inicio!=null){
            if(Inicio.info.llave.equals(valorE.llave)){
                Inicio=Inicio.siguiente;
            }
            else{
                recorre=Inicio;
                anterior=recorre;
                while((recorre.siguiente!=null)&&(!recorre.info.llave.equals(valorE.llave))){
                    anterior=recorre;
                    recorre=recorre.siguiente;
                }
                if(recorre.info.llave.equals(valorE.llave))
                    anterior.siguiente=recorre.siguiente;
            }
        }
    }
    
    public Nodo buscar(TDAToken valorE){
        Nodo recorre, anterior;
        
        //si no se encuentra el objeto con la llave que coincida, se devuelve un nodo error con categoria 0
        TDAToken errorToken = new TDAToken();
        errorToken.categoria = 0;
        Nodo error = new Nodo(errorToken);
        
        if(Inicio!=null){
            if(Inicio.info.llave.equals(valorE.llave)){
                //Inicio=Inicio.siguiente;
               return Inicio; 
            }
            else{
                recorre=Inicio;
                anterior=recorre;
                while((recorre.siguiente!=null)&&(!recorre.info.llave.equals(valorE.llave))){
                    anterior=recorre;
                    recorre=recorre.siguiente;
                }
                if(recorre.info.llave.equals(valorE.llave)){
                    //anterior.siguiente=recorre.siguiente;
                    return recorre;
                }
                else
                    return error;
            }
        }
        
        return error;
    }
    
}
