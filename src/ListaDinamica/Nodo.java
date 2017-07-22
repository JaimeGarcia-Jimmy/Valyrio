

package ListaDinamica;


public class Nodo {
    public TDAToken info;
    public Nodo siguiente;
    
    public Nodo(TDAToken I){
        info=I;
        siguiente=null;
    }
    
    public Nodo(TDAToken I, Nodo S){
        info=I;
        siguiente=S;
    }
}
