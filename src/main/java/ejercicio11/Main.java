package ejercicio11;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {

    public final static Scanner sc = new Scanner(System.in);
    public final static String txt = "Hola que tal estamos";

    public static void main(String[] args) {
        File file = new File("F:\\2ºDAM\\Acceso a datos\\Pruebas\\random.txt");
        File fileTxt = new File("F:\\2ºDAM\\Acceso a datos\\Pruebas\\randomTxt.txt");
        escrituraRandom(file);
        lecturaRandom(file);
        escrituraRandom(fileTxt, txt);
        lecturaRandomTxt(fileTxt);
        System.out.println();
        lecturaRandomTxt(fileTxt, 3);
        
    }

    /**
     * Este metodo utiliza el RandomAccessFile para escribir un objeto en un fichero.
     * @param file archivo donde vamos a escribir
     */
    public static void escrituraRandom(File file){
        Persona data = new Persona("46289242V", "Brais", "Ferro", 23, "Masculino");
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw");ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)){

            oos.writeObject(data);
            byte[] a = baos.toByteArray();
            raf.write(a);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo se encarga de leer los datos de un objeto de un fichero a traves de RandomAccesFile
     * @param file fichedo de donde vamos a sacar los datos
     */
    public static void lecturaRandom(File file){
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){
            raf.seek(0);
            byte[] a = new byte[(int)raf.length()];
            raf.readFully(a);
            bais = new ByteArrayInputStream(a);
            ois = new ObjectInputStream(bais);

            System.out.println(((Persona)ois.readObject()).toString());

        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este metodo se encarga de leer escribir de un fichero
     * @param file Archivo donde queremos escribir
     * @param txt texto que queremos escribir
     */
    public static void escrituraRandom(File file, String txt){
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.writeChars(txt);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo se encarga de leer texto de un fichero
     * @param file
     */
    public static void lecturaRandomTxt(File file){
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            for (int i = 0; i < txt.length(); i++) {
                System.out.print(raf.readChar());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo se encarga de leer texto de un fichero desde la posicion dada.
     * @param file archivo que se quiere leer
     * @param pos posicion desde la que se quiere leer
     */
    public static void lecturaRandomTxt(File file, int pos){
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(pos);
            for (int i = 0; i < txt.length()-pos; i++) {
                System.out.print(raf.readChar());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}



