package ejercicio9;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("F:\\2ºDAM\\Acceso a datos\\Pruebas\\Prueba.txt");
        //Ficheiro.escritura(file);
        //Ficheiro.lectura(file);
        //System.out.println("CAMBIO");
        Ficheiro.escrituraUTF(file);
        Ficheiro.lecturaUTF(file);
    }
}
