package ejercicio5.forma1;

import ejercicio5.forma1.CountMap;

public class Main {
    public static void main(String[] args) {
        CountMap<String> mapa = new CountMap<>();
        mapa.count("Hola");
        mapa.count("Hola");
        mapa.count("Hola");
        mapa.count("Hola");
        mapa.count("Juan");
        mapa.toStringCount();
        mapa.discount("Hola");
        mapa.discount("Juan");
        mapa.toStringCount();
        mapa.delete("Hola");
        mapa.toStringCount();
    }
}
