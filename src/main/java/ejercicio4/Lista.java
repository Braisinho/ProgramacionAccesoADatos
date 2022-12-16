package ejercicio4;

public class Lista<T> {

        //O primer elemento da lista
        private NodeList<T> first = null;
        //O tamaño da lista
        private int length = 0;

        /**
         Devolve o tamaño.
         */
        public int size()
        {
            return this.length;
        }

        /**
         Engade un elemento ao final da lista

         @param elem: O valor do elemento a engadir.
         */
        public void add(T elem)
        {
            NodeList<T> act = new NodeList<T>(elem); //Créase un novo elemento
            if(this.first != null) //Se a lista non está baleira
            {
                NodeList<T> last = this.first;
                //Busca o nodo que non teña seguinte, é dicir, o último.
                while(last.getNext() != null)
                {
                    last = last.getNext();
                }
                last.setNext(act);
                act.setPrev(last);
            }else{
                this.first = act;
            }

            this.length++;
        }

        /**
         Busca un elemento dado, un índice, e devólveo.

         @param index: O índice do elemento a buscar comenzando en 0
         */
        private NodeList<T> search(int index)
        {
            NodeList<T> act = this.first;
            for(int i = 0; i < index; i++)
            {
                act = act.getNext();
            }
            return act;
        }

        /**
         Devolve o valor do elemento nunha posición dada.

         @param index: A posición do elemento a buscar comezando en 0.
         */
        public T get(int index) throws IndexOutOfBoundsException
        {
            //Se o índice está dentro dos parámetros.
            if(index >= 0 && index < this.length)
            {
                NodeList<T> elem = search(index);
                return elem.getElem();
            }
            throw new IndexOutOfBoundsException();
        }

        /**
         Muda o valor do elemento nunha posición dada.

         @param index: A posición do elemento a cambiar comenzando en 0.
         @param elem: O valor a establecer
         */
        public void set(int index, T elem) throws IndexOutOfBoundsException
        {
            //Se o índice está dentro dos parámetros.
            if(index >= 0 && index < this.length)
            {
                NodeList<T> act = search(index);
                act.setElem(elem);
            }else{
                throw new IndexOutOfBoundsException();
            }
        }

        /**
         Elimina o elemento nunha posición dada.

         @param index: A posición do elemento a borrar comenzando en 0.
         */
        public void delete(int index)
        {
            if(index >= 0 && index < this.length && this.first != null)
            {
                if(this.length == 1)
                {
                    this.first = null;
                }else{
                    if(index == 0)
                    {
                        this.first = this.first.getNext();
                        this.first.setPrev(null);
                    }else{
                        NodeList<T> act = search(index);
                        act.getPrev().setNext(act.getNext());
                        if(act.getNext() != null)
                        {
                            act.getNext().setPrev(act.getPrev());
                        }
                    }

                }
                this.length--;
            }
        }

        /**
         Mostra por pantalla o valor de todos os elementos da lista.
         */
        public String toString()
        {
            if(this.length > 0)
            {
                NodeList<T> act = this.first;
                String list = "[" + act.getElem();
                for(int i = 1; i < this.length; i++)
                {
                    act = act.getNext();
                    list += "," + act.getElem();
                }
                return list + "]";
            }else{
                return "Lista baleira";
            }
        }
    }

