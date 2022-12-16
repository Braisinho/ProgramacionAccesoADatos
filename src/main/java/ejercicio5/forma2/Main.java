package ejercicio5.forma2;

public class Main {
    public static void main(String[] args) {

       Count<String> lista = new Count<>();
       lista.count("Hola");
        lista.count("Hola");
        lista.count("Hola");
        lista.count("Hola");
        lista.count("Juan");
        lista.count("Juan");
        System.out.println(lista.toStringCount());
        lista.disCount("Hola");
        System.out.println(lista.toStringCount());
        lista.delete("Juan");
        System.out.println(lista.toStringCount());

    }
}
