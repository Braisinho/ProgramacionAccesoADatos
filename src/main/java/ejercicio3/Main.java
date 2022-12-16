package ejercicio3;

public class Main {
    public static void main(String[] args) {

        ABB arbol = new ABB();
        arbol.add(50);
        arbol.add(25);
        arbol.add(15);
        arbol.add(30);
        arbol.add(10);
        arbol.add(18);
        arbol.add(28);
        arbol.add(40);
        arbol.add(29);
        arbol.add(27);
        arbol.add(26);

        System.out.println(arbol.delete(25));
        System.out.println(arbol.deep(40));
        System.out.println(arbol.exist(90));

    }

}
