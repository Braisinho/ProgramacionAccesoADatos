package ejercicio5.forma2;

public class NodeList<T> {

        //O propio elemento
        private T elem;
        //A referencia ao seguinte elemento
        private NodeList<T> next = null;
        //A referencia ao anterior elemento
        private NodeList<T> prev = null;

        public NodeList(T elemento)
        {
            this.elem = elemento;
        }

        //Getters
        public T getElem()
        {
            return this.elem;
        }
        public NodeList<T> getNext()
        {
            return this.next;
        }
        public NodeList<T> getPrev()
        {
            return this.prev;
        }

        //Setters
        public void setElem(T elemento)
        {
            this.elem = elemento;
        }
        public void setNext(NodeList<T> siguiente)
        {
            this.next = siguiente;
        }
        public void setPrev(NodeList<T> anterior)
        {
            this.prev = anterior;
        }
    }

