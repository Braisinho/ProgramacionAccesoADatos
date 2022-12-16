package ejercicio5.forma1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountMap<T>{

    private Map<T, Integer> datos = new HashMap<>();

    /**
     *Para esta forma usamos un mapa con clave valor, la clave sera el elemento y el valor el contador
     * Lo que haremos sera comprobar si ya tenemos ese elemento, en caso contrario lo añadimos, en caso
     * de que lo tengamos lo añadimos otra vez(sobreescribiendolo) con su nuevo valor (contador).
     * @param element elemento que queremos añadir.
     */
    public void count(T element){
        Integer countElement = this.datos.get(element);
        if (countElement == null){
            this.datos.put(element,1);
        }else{
            this.datos.put(element, ++countElement);
        }
    }

    /**
     * Lo que haremos sera buscar en elemento, en caso de existir reduciremos su valor en 1,en caso de que su valor
     * antes de la reduccion sea 1, lo eliminaremos (1-0 = 0)
     * @param element elemento que queremos descontar.
     */
    public void discount(T element){
        Integer countElement = this.datos.get(element);
        if (countElement != null){
            if (countElement == 1){
                this.datos.remove(element);
            }else{
                this.datos.put(element, --countElement);
            }
        }
    }

    /**
     * El metodo remove elimina un elemento por su clave.
     * @param element elemento que queremos elimiar.
     */
    public void delete(T element){
        datos.remove(element);
    }


    /**
     * Iteramos el mapa y lo vamos mostrando por pantalla.
     */
    public void toStringCount() {
        Iterator<T> it = this.datos.keySet().iterator();
        while (it.hasNext()){
            T clave = it.next();
            Integer valor = this.datos.get(clave);
            System.out.println("Clave: " + clave + " Count: " +valor);
        }
    }
}
