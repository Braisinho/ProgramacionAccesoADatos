package ejercicio1;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.println("Memoria vacia\n");
        memoria();


        int[] arrayMemoria = new int[100];

        System.out.println("Array inicializado\n");
        memoria();

        for (int i = 0; i<100; i++){
            arrayMemoria[i] = i;
        }

        System.out.println("Valores añadidos al array\n");
        memoria();

        ArrayList<Integer> arrayListMemoria = new ArrayList<>();

        System.out.println("ArrayList creado\n");
        memoria();

        for (int i = 0; i<10000; i++){
            arrayListMemoria.add(i);
        }

        System.out.println("Añadidos 100 elementos en el ArrayList\n");
        memoria();

        for (int i = 0; i<10000000; i++){
            arrayListMemoria.add(i);
        }

        System.out.println("Añadidos 10000 elementos en el ArrayList\n");
        memoria();
    }

    public static void memoria(){
        int dataSize = 1024 * 1024;

        Runtime runtime = Runtime.getRuntime();

        System.out.println ("Memoria máxima: " + runtime.maxMemory() / dataSize + "MB");
        System.out.println ("Memoria total: " + runtime.totalMemory() / dataSize + "MB");
        System.out.println ("Memoria libre: " + runtime.freeMemory() / dataSize + "MB");
        System.out.println ("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + "MB\n");
    }

    /*
    * Como podemos observar, cuando creamos un array y luego le asignamos valores, la memoria usada
    * no varia, esto es porque, cuando se crea un array, al darle ya un tamaño, ya se reserva el espacio en
    * memoria.
    * Por otra parte, cuando utilizamos el arrayList, al ser dinamico, se va usando memoria a la vez que se va necesitando,
    * por eso la memoria varia entre la usada en la instancia del arrayList y la usada en la introduccion de valores dentro de este.
    * */
}
