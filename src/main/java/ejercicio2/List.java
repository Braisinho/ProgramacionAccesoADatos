package ejercicio2;



public class List {

    private NodeList firstElement = null;

    private int length = 0;

    public int size(){

        return this.length;
    }

    /**
     *
     * @param element Valor del elemento que queremos guardar;
     *
     * Este metodo añade un nuevo elemento, lo primero que comprobamos es que si no tenemos elementos guardados
     * añadidos el elemento en firstElement, en cambio si tenemos elementos, recorremos los elementos hasta llegar
     * al ultimo y colocamos este elemento a continuacion. Luego aumentamos el tamaño de length.
     */
    public void add(int element){

        NodeList act = new NodeList(element);
        act.setNextElement(null);
        NodeList aux = firstElement;

        if (length == 0){
            this.firstElement = act;
        }else{
            for (int i = 0; i<this.length; i++){
                if (aux.getNextElement() !=null){

                    aux = aux.getNextElement();
                }else{
                    aux.setNextElement(act);
                    act.setPrevElement(aux);
                }
            }
        }


        this.length++;
    }

    /**
     *
     * @param index Posicion de la que se quiere conocer el valor
     * @return Devuelve el valor requerido
     * Para esto primero comprobamos que el valor pasado esta dentro de los parametros de tamaño,
     * una vez comprobado, recorremos la lista el numero de veces necesarias hata llegar a la posicion
     * deseada y devolvemos el valor.
     */

    public int get(int index){

        if (index > 0 && index<=this.length){

            NodeList aux = firstElement;

            for (int i = 1; i<index; i++){
                aux = aux.getNextElement();
            }
            return aux.getElement();

        }return -1;
    }

    /**
     *
     * @param index Posicion de la que se quiere modificar el valor
     * @param element Nuevo valor del elemento
     * @return True si funciona o false sino
     * Para esto primero comprobamos que el valor pasado esta dentro de los parametros de tamaño,
     * una vez comprobado, recorremos la lista el numero de veces necesarias hata llegar a la posicion
     * deseada. Una vez encontrada cambiamos el valor del atributo element.
     */

    public boolean set(int index, int element){

        if (index > 0 && index<=this.length){

            NodeList aux = firstElement;

            for (int i = 1; i<index; i++){
                aux = aux.getNextElement();
            }
            aux.setElement(element);
            return true;

        }return false;
    }

    /**
     *
     * @param index Posicion del elementos que deseamos eliminar
     * @return Devuelve un boolean si funciona correctamente.
     * Para esto primero comprobamos que el valor pasado esta dentro de los parametros de tamaño,
     * una vez comprobado, recorremos la lista el numero de veces necesarias hata llegar a la posicion
     * deseada. Una vez encontrada comprobamos si estamos en la primera o la ultima, en caso de ser la
     * primera lo que hacemos es asignar el elemento 2 (que sera el nuevo 1) a la variable firstElement
     * y luego se eliminan los punteros, en caso de estar en la ultima posicion, asignamos null al puntero previo
     * En caso de estar entre el medio, lo que hacemos es mover los punteros de los adyacentes.
     */

    public boolean delete(int index){

        if (index > 0 && index<=this.length){

            NodeList aux = firstElement;

            for (int i = 1; i<index; i++){
                aux = aux.getNextElement();
            }
            if (index == 1){
                this.firstElement = aux.getNextElement();
                aux.getNextElement().setPrevElement(null);
                aux.setNextElement(null);

            }else if(index == this.length){
                aux.getPrevElement().setNextElement(null);
                aux.setPrevElement(null);
            }else{
                aux.getPrevElement().setNextElement(aux.getNextElement());
                aux.getNextElement().setPrevElement(aux.getPrevElement());
            }
            this.length--;
            return true;

        }return false;
    }

    /**
     * Muestra toda la lista
     */

    public void show(){

        NodeList aux = firstElement;

        for (int i = 1; i<=this.length; i++){
            System.out.println(i + " : " + aux.getElement());
            aux = aux.getNextElement();
        }

    }
}
