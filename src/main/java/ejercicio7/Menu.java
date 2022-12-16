package ejercicio7;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    /**
     * Este metodo invocara a cada opcion del menu.
     * @param option valor del menu
     * @param file archivo base
     */
    public static void menuOptions(int option, File file){
        switch (option){
            case 1: list(file);
            break;
            case 2: viewPermissions(file);
            break;
            case 3: createFile(file);
            break;
            case 4: createDirectory(file);
            break;
            case 5: delete(file);
            break;
            case 6: rename(file);
            break;
            case 7: readFile(file);
            break;
            case 8: writeFile(file);
            break;
            default:
                System.out.println("Introduce una opcion de la lista \n");
                break;
        }
    }

    /**
     * Este metodo lista los archivos
     * @param file archivo para listar
     */
    private static void list(File file){
        System.out.println(Arrays.toString(file.list()));
    }

    /**
     * Este metodo se encarga de comprobar los permisos de un archivo.
     * @param file arvhico para consultar permisos
     */
    private static void viewPermissions(File file){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        System.out.println("Selecciona sobre que fichero quieres visualizar los permisos");
        String[] ficheros = file.list();
        if (ficheros != null) {
            readDirectory(file, ficheros);
            try{
                option = sc.nextInt();
                File filePermissions = new File(ficheros[option-1]);
                System.out.println("Permiso de ejecucion: " + ((filePermissions.canExecute() ? "Si" : "No")) +"\n"+
                        "Permiso de escritura: " + ((filePermissions.canWrite() ? "Si" : "No")) +"\n" +
                        "Permiso de lectura: " + ((filePermissions.canRead() ? "Si" : "No")) );
            }catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("Error a la hora de introducir datos");
            }
        }
    }

    /**
     * Este metodo crea un nuevo archivo con el nombre que se le indique.
     * @param file carpeta donde se creara el nuevo archivo
     */
    private static void createFile(File file){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del Archivo");
        String fileName = sc.next();
        File a = new File(file + "\\"+fileName);
        try {
            a.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo crea un directorio con el nombre indicado
     * @param file carpeta donde se creara el directorio
     */
    private static void createDirectory(File file){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del directorio");
        String fileName = sc.next();
        File create = new File(file + "\\"+fileName);
        create.mkdir();
    }

    /**
     * Este metodo borrara el archivo indicado por el usuario.
     * @param file directorio del archivo
     */
    private static void delete(File file){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        System.out.println("Selecciona sobre que fichero quieres borrar");
        String[] ficheros = file.list();
        if (ficheros != null) {
            readDirectory(file, ficheros);
            option = sc.nextInt();
            try {
                File delete = new File(file +"\\"+ficheros[option-1]);
                delete.delete();
            }catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("Error a la hora de introducir datos");
            }
        }
    }

    /**
     * Este metodo se usa para renombrar el arvhico.
     * @param file directorio del archivo
     */
    private static void rename(File file){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        System.out.println("Selecciona sobre que fichero quieres renombrar");
        String[] ficheros = file.list();
        if (ficheros != null) {
            readDirectory(file, ficheros);
            option = sc.nextInt();
            try {
                File oldname = new File(file +"\\"+ficheros[option-1]);
                System.out.println("Introduce el nombre del nuevo archivo");
                String newNameStr = sc.next();
                File newName = new File(file +"\\"+newNameStr);
                oldname.renameTo(newName);
            }catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("Error a la hora de introducir datos");
            }
        }
    }

    /**
     * Este metodo utiliza fileReader para leer los archivos.
     * @param file directorio del archivo
     */
    private static void readFile(File file){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        System.out.println("Selecciona sobre que fichero quieres renombrar");
        String[] ficheros = file.list();
        if (ficheros != null) {
            readDirectory(file, ficheros);
            option = sc.nextInt();
            FileReader fr = null;
            try {
                File read = new File(file +"\\"+ficheros[option-1]);
                if (read.isFile()){
                    fr = new FileReader(read);
                    int letter = fr.read();
                    while (letter != -1){
                        System.out.print((char) letter);
                        letter = fr.read();
                    }

                }else{
                    System.out.println("No es un documento que podamos leer");
                }

            }catch (InputMismatchException | ArrayIndexOutOfBoundsException | IOException e){
                System.out.println("Error a la hora de introducir datos");
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

    /**
     * Este metodo se usara para escribir en el archivo indicado por el usuario.
     * @param file directorio del archivo
     */
    private static void writeFile(File file){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        System.out.println("Selecciona sobre que fichero quieres renombrar");
        String[] ficheros = file.list();
        if (ficheros != null) {
            readDirectory(file, ficheros);
            option = sc.nextInt();
            FileWriter fw = null;
            try {
                File write = new File(file +"\\"+ficheros[option-1]);
                if (write.isFile()){
                    System.out.println("Que desea escribir?");
                    sc.nextLine();
                    String txt = sc.nextLine();
                    fw = new FileWriter(write);
                    fw.write(txt);
                }else{
                    System.out.println("No es un documento que se pueda escribir");
                }

            }catch (InputMismatchException | ArrayIndexOutOfBoundsException | IOException e){
                System.out.println("Error a la hora de introducir datos");
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
    }

    private static void readDirectory(File file,String[] ficheros){
        for (int i = 0; i<ficheros.length; i++){
            System.out.println(i+1 +" : "+ ficheros[i]);
        }
    }

}
