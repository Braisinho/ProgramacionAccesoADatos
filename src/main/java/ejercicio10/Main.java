package ejercicio10;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static final File fileBuffer = new File("F:\\2ºDAM\\Acceso a datos\\Pruebas\\PruebaBuffer.txt");
    private static final File fileStream = new File("F:\\2ºDAM\\Acceso a datos\\Pruebas\\PruebaStream.txt");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String txt;
        System.out.println("Introduce el texto que quieras guardar");
        txt = sc.nextLine();

        /*
         *Probamos el Stream
         */
        System.out.println("Stream");
        Stream.streamWriter(fileStream, txt);
        Stream.streamReader(fileStream);


        /*
         *Probamos el Buffer
         */
        System.out.println("Buffer");
        Buffer.bufferWriter(fileBuffer,txt);
        Buffer.bufferReader(fileBuffer);

    }
}
