package ejercicio3;

public class NodeTree {

    private int value;
    private NodeTree leftNodeTree;
    private NodeTree rightNodeTree;

    public NodeTree(int valueNodo){
        this.value = valueNodo;
        this.leftNodeTree = null;
        this.rightNodeTree = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeTree getLeftNodo() {
        return leftNodeTree;
    }

    public void setLeftNodo(NodeTree leftNodeTree) {
        this.leftNodeTree = leftNodeTree;
    }

    public NodeTree getRightNodo() {
        return rightNodeTree;
    }

    public void setRightNodo(NodeTree rightNodeTree) {
        this.rightNodeTree = rightNodeTree;
    }

    @Override
    public String toString() {
        return "NodeTree{" +
                "value=" + value +
                '}';
    }
}
