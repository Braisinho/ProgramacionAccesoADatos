package ejercicio2;

public class NodeList {

    private int element;

    private NodeList nextElement;

    private NodeList prevElement;

    public NodeList(int element){

        this.element = element;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public NodeList getNextElement() {
        return nextElement;
    }

    public void setNextElement(NodeList nextElement) {
        this.nextElement = nextElement;
    }

    public NodeList getPrevElement() {
        return prevElement;
    }

    public void setPrevElement(NodeList prevElement) {
        this.prevElement = prevElement;
    }
}
