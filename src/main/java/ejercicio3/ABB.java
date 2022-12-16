package ejercicio3;


public class ABB {

    private NodeTree raiz;

    /**
     *
     * @param value valor del nodo introducido;
     *Este metodo comprueba si la raiz es null, en caso de serlo, introduce el nuevo NodeTree en esta, en
     *caso contrario, comprueba si el nodo ya existe y si no, llama al metodo privado add pasandole la raiz y el valor.
     */
    public void add(int value) {
        if (this.raiz == null) {
            this.raiz = new NodeTree(value);
        } else {
            if (!exist(value)){
                this.add(this.raiz, value);
            }
        }
    }

    /**
     *
     * @param nodeTree el nodo previo
     * @param value el valor del nuevo nodo
     * Este metodo utiliza la recursividad para avanzar en el arbol, una vez encontrado un lugar a null añade el nuevo nodo.
     * La forma de usar la recursividad es la siguiente, en caso de que el nodeTree no tenga espacios libres, comprobamos
     * si el nuevo nodo tiene que ir a derecha o izquierda, cuando lo sepamos, invocamos otra vez al metodo pero con el hijo de NodeTree
     * que corresponda para comprobar sus hijos, asi hasta encontrar un huevo libre.
     */
    private void add(NodeTree nodeTree, int value) {
        if (value > nodeTree.getValue()) {
            if (nodeTree.getRightNodo() == null) {
                nodeTree.setRightNodo(new NodeTree(value));
            } else {
                this.add(nodeTree.getRightNodo(), value);
            }
        } else {
            if (nodeTree.getLeftNodo() == null) {
                nodeTree.setLeftNodo(new NodeTree(value));
            } else {
                this.add(nodeTree.getLeftNodo(), value);
            }
        }
    }


    /**
     *
     * @param value valor del nodo que se desea conocer su existencia.
     * @return true si existe, false si no.
     */
    public boolean exist(int value) {
        return exist(this.raiz, value);
    }

    /**
     *
     * @param nodeTree nodo previo
     * @param value valor del nodo que se desea comprobar
     * @return true si existe, false si no.
     * El metodo de recursividad es el mismo, solo que ahora paramos la resursividad cuando algun hijo de nodeTree tiene como valor el valor deseado.
     */
    private boolean exist(NodeTree nodeTree, int value) {
        if (nodeTree == null) {
            return false;
        }
        if (nodeTree.getValue() == value) {
            return true;
        } else if (value < nodeTree.getValue()) {
            return exist(nodeTree.getLeftNodo(), value);
        } else {
            return exist(nodeTree.getRightNodo(), value);
        }
    }


    /**
     *
     * @param value valor del nodo que se desea borrar.
     * @return true si existe, false si no.
     * Este metodo es el encargado de borrar una hoja, es decir, un nodo sin hijos.
     * Previamente se comprueba que el valor deseado existe.
     */
    public boolean deleteLeaf(int value){
        if (exist(value)){
            return deleteLeaf(this.raiz, value);
        }
        return false;
    }

    /**
     *
     * @param nodeTree nodo previo
     * @param value valor del nodo que se desea borrar.
     * @return true si existe, false si no.
     * Este metodo recorre el arbol de la misma forma que los anteriores y para cuando algun hijo del nodo actual tiene como valor un valor igual al pedido.
     * Una vez encontrado ese nodo, lo que hacemos es comprobar que realmente sea una hoja, es decir, que no tiene hijos y procedemos a asignar al padre el valor null al lado que le corresponda.
     */
    private boolean deleteLeaf(NodeTree nodeTree, int value){

        if (nodeTree == null) {
            return false;
        }
        if (nodeTree.getRightNodo() != null && nodeTree.getRightNodo().getValue() == value ) {
            if (nodeTree.getRightNodo().getRightNodo() == null && nodeTree.getRightNodo().getLeftNodo() == null){
                nodeTree.setRightNodo(null);
                return true;
            }
            return false;
        }
        if (nodeTree.getLeftNodo() != null && nodeTree.getLeftNodo().getValue() == value){
            if (nodeTree.getLeftNodo().getLeftNodo() == null && nodeTree.getLeftNodo().getRightNodo() == null){
                nodeTree.setLeftNodo(null);
                return false;
            }
            return false;
        }
        else if (value < nodeTree.getValue()) {
            return deleteLeaf(nodeTree.getLeftNodo(), value);
        } else {
             return deleteLeaf(nodeTree.getRightNodo(), value);
        }
    }

    /**
     *
     * @param value valor del cual se quiere conocer su tamaño.
     * @return el tamaño (-1 si el valor pedido no existe.)
     * Utilizamos un contador.
     */
    public int deep(int value){
        if (exist(value)){
            this.cont = 0;
            return deep(this.raiz, value);
        }
        return -1;
    }

    private int cont = 0;

    /**
     *
     * @param nodeTree nodo previo
     * @param value valor del cual se quiere conocer tu tamaño
     * @return valor del tamaño
     * Como siempre, utilizaremos recursividad para llegar al nodo deseado, pero esta vez por cada paso por resursividad, añadiremos un +1 al contador.
     * Este contador se resetea en el metodo previo.
     */
    private int deep(NodeTree nodeTree, int value){
        if (nodeTree == null) {
            return -1;
        }
        if (nodeTree.getValue() == value) {
            return cont;
        } else if (value < nodeTree.getValue()) {
            cont++;
            return deep(nodeTree.getLeftNodo(), value);
        } else {
            cont++;
            return deep(nodeTree.getRightNodo(), value);
        }
    }

    /**
     *
     * @param value valor que se quiere eliminar
     * @return true si lo elimima, false si no.
     * Primero comprobamos si el valor existe en el arbol.
     */
    public boolean delete(int value){
        if (exist(value)){
            return delete(this.raiz, value);
        }
        return false;
    }

    /**
     *
     * @param nodeTree valor del nodo que se quiere comprobar
     * @param value valor del nodo que se quiere borrar
     * @return true si se borra, false si no.
     * Este metodo es el mas complejo, pues tiene que borrar cualquier nodo, sea hoja o no.
     * Para eso lo primero es encontrar el nodo, para eso usaremos la recursividad como hasta ahora.
     * Una vez encontrado, comprobaremos los hijos se tiene, en caso de no tener hijos usaremos el metodo deleteleaf().
     * En caso de tener 1 hijo lo que haremos sera enlazar el padre del nodo que queremos borrar, con el hijo de este asi eliminado los punteros al padre.
     * En caso de tener 2 hijos lo que hice en este caso, es buscar el hijo del nodo que queremos borrar que mas se aproxime en valor, es decir, si el nodo que queremos
     * borrar tiene como valor 20 y tiene como hijos a nodos con valores 25,40,22,21 usaremos el nodo con valor 21. Usaremos ese nodo para colocarlo en su posicion y reenlazar
     * los punteros de los hijos y del padre. Para conocer el hijo mas proximo y al padre, usaremos los metodos father() y eldestSOn() que estan explicados mas abajo.
     * Una vez eliminado el nodo, comprobaremos si hemos eliminado la raiz, en caso de eliminarla, actualizaremos el valoor de this.raiz para que el resto de metodos siga funcionando.
     */
    private boolean delete(NodeTree nodeTree, int value){
        if (nodeTree == null) {
            return false;
        }
        if (nodeTree.getValue() == value) {
            if (nodeTree.getLeftNodo() != null && nodeTree.getRightNodo() != null){
                if (father(value).getValue() > value){
                    father(value).setLeftNodo(eldestSon(nodeTree));
                    eldestSon(nodeTree).setRightNodo(nodeTree.getRightNodo());
                    eldestSon(nodeTree).setLeftNodo(nodeTree.getLeftNodo());
                }else{
                    father(value).setRightNodo(eldestSon(nodeTree));
                    eldestSon(nodeTree).setRightNodo(nodeTree.getRightNodo());
                    eldestSon(nodeTree).setLeftNodo(nodeTree.getLeftNodo());
                }
            }else if (nodeTree.getLeftNodo() != null){
                    if (father(value).getValue() > value){
                        father(value).setLeftNodo(nodeTree.getLeftNodo());
                    }else{
                        father(value).setRightNodo(nodeTree.getLeftNodo());
                    }
            }else if (nodeTree.getRightNodo() != null){
                if (father(value).getValue() > value){
                    father(value).setLeftNodo(nodeTree.getRightNodo());
                }else{
                    father(value).setRightNodo(nodeTree.getRightNodo());
                }
            }else{
                deleteLeaf(value);
            }
            if (value == this.raiz.getValue()){
                this.raiz.setValue(eldestSon(nodeTree).getValue());
            }
            return true;
        } else if (value < nodeTree.getValue()) {
            return delete(nodeTree.getLeftNodo(), value);
        } else {
            return delete(nodeTree.getRightNodo(), value);
        }
    }

    /**
     *Este metodo solo se usara en el metodo delete.
     * @param nodeTreeDeleted nodo que se quiere borrar.
     * @return hijo inmediatamente mas cercan del nodo que se quiere borrar.
     */
    private NodeTree eldestSon(NodeTree nodeTreeDeleted){
        NodeTree eldestSon = nodeTreeDeleted.getRightNodo();

        while(eldestSon.getLeftNodo() != null){
            eldestSon = eldestSon.getLeftNodo();
        }

        return eldestSon;
    }

    /**
     *
     * @param value valor del nodo del cual se quiere saber el padre.
     * @return el padre.
     */
    private NodeTree father(int value){
        if (exist(value)){
            return father(this.raiz, value);
        }
        return null;
    }

    /**+
     * *Este metodo solo se usara en el metodo delete.
     * @param nodeTree nodo a comprobar
     * @param value valor del hijo
     * @return el padre del hijo
     * Utilizando recursividad coomo hizimos antes pero parando cuando algunos de los hijos de nodeTree se corresponde con el valor.
     */
    private NodeTree father(NodeTree nodeTree, int value){
        if (nodeTree.getRightNodo() != null && nodeTree.getRightNodo().getValue() == value){
            return nodeTree;
        }
        if (nodeTree.getLeftNodo() != null && nodeTree.getLeftNodo().getValue() == value){
            return nodeTree;

        } else if (value < nodeTree.getValue()) {
            return father(nodeTree.getLeftNodo(), value);
        } else {
            return father(nodeTree.getRightNodo(), value);
        }
    }

}
