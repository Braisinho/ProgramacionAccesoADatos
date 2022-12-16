package ejercicio5.forma2;



public class Count <T>{

    private Lista<T> listElements = new Lista<>();
    private Lista<Integer> listCount = new Lista<>();

    /**
     *Este metodo se usara para introducir un valor, en caso de no existir, o aumentar el contador de ese elemento en caso de ya existir.
     * Lo que haremos sera realizar un bucle tantas veces como elementos diferentes tenemos, si el nuevo elemento ya existe, aumentamos en +1
     * su valor en la lista del contador, en caso de no existir, añadiremos el elemento en la lista de elementos y una nueva entrada en la lista del
     * contador.
     * @param element elemento que queremos introducir.
     */
    public void count(T element){
        for (int i = 0; i< this.listElements.size(); i++){
            if (listElements.get(i).equals(element)){
                listCount.set(i, listCount.get(i)+1);
                return;
            }
        }
        listElements.add(element);
        listCount.add(1);
    }

    /**
     * Este metodo se usa para reducir el contador sobre un elemento, en caso de que este contador tenga el valor de 1 antes de realizar la resta, lo eliminaremos
     * de ambas listas, en caso de que sea distinto a 1, lo que haremos sera buscar la posicion de ese elemento, una vez encontrada vamos a esa misma posicion de la
     * otra lista y disminuimos su valor
     * @param element elemento que queremos decrementar.
     */
    public void disCount(T element){
        for (int i = 0; i< this.listElements.size(); i++){
            if (listElements.get(i).equals(element)){
                Integer count = listCount.get(i);
                if (count == 1){
                    listCount.delete(i);
                    listElements.delete(i);
                }else{
                    listCount.set(i, count-1);
                }
            }
        }
    }

    /**
     * Este metodo se usara para eliminar un elemento y su contador asociado, recorreremos la lista hasta encontrarlo, una vez encontrado eliminamos de las dos listas ya que
     * compartes posicion.
     * @param element elemento que queremos borrar.
     */
    public void delete(T element){
        for (int i = 0; i<this.listElements.size(); i++){
            if (listElements.get(i).equals(element)){
                listElements.delete(i);
                listCount.delete(i);
            }
        }
    }

    /**
     * Este metodo se usara para visualizar las 2 listas con el formato indicado por el profesor.
     * @return String con la salida.
     */
    public String toStringCount(){
        String exit = "[";
        for (int i = 0; i<this.listElements.size(); i++){
            exit += (this.listElements.get(i) + ":" + this.listCount.get(i) +",");
        }
        exit = exit.substring(0, exit.length()-1);
        exit += ("]");
        return exit;
    }
}
