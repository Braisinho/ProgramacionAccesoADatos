package ejercicio9;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * NOTA: Odilo lembra que non conseguimos que o fileWriter mostrame mal os datos, codificamos igualmente.
 */
public class Ficheiro {

    /**
     * Este metodo utiiza la codificacion UTF-8 para escribir en un archivo .txt
     * @param file archivo donde vamos a guardar el texto.
     */
    public static void escrituraUTF(File file){
        Scanner sc = new Scanner(System.in);
        String txt;
        System.out.println("Introduce el texto que quieras guardar");
        txt = sc.nextLine();
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8);
            out.write(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Este metodo utiliza la codificacion UTF-8 para leer los datos de un archivo.
     * @param file archivo del cual vamos a recoger el texto
     */
    public static void lecturaUTF(File file){
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(Files.newInputStream(file.toPath()),StandardCharsets.UTF_8);
            int letter = in.read();
            while (letter != -1){
                System.out.print((char) letter);
                letter = in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Este metodo no utiliza codificacion UTF-8 para escribir, asi que algunos caracteres no seran reconocidos.
     * @param file archivo donde vamos a escribir el texto
     */
    public static void escritura(File file){
        Scanner sc = new Scanner(System.in);
        String txt;
        System.out.println("Introduce el texto que quieras guardar");
        txt = sc.nextLine();
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(txt);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Este metodo no va a utilizar codificacion UTF-8 asi que algunso caracteres no se podran visualizar.
     * @param file archivo del cual vamos a leer
     */
    public static void lectura(File file){
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            int letter = fr.read();
            while (letter != -1){
                System.out.print((char) letter);
                letter = fr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
