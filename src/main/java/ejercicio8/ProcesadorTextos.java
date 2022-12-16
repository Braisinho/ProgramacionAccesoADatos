package ejercicio8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProcesadorTextos {

    private File file;
    private String txt="";

    public ProcesadorTextos() {
        askFile();
        takeTextFromTerminal();
    }

    /**
     * Este metodo se encarga de pedir al usuario la ruta donde se alojara el archivo, en caso
     * de que la ruta no existe, la creamos y luego pedimos el nombre del archivo que se quiera crear.
     */
    private void askFile(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta completa del archivo");
        String filePath = sc.nextLine();
        file = new File(filePath);
        if (!file.exists()){
            try {
                Files.createDirectories(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Introduce el nombre del fichero, no te olvides de la extension");
        String fileName = sc.nextLine();
        file = new File(filePath + "\\" + fileName+".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo recoge el texto que por terminal, para eso lo primero es hacer un bucle
     * que se acabara cuando escribamos la palabra parar, mientras tanto, lo que vamos
     * escribiendo se guarda en un String donde luego al escribir guardar, se guardara completamente
     * en el archivo.
     */
    private void takeTextFromTerminal(){
        Scanner sc = new Scanner(System.in);
        String temporalString ="";
        System.out.println("Introduce el texto que quieras escribir\n Esta seran las opciones: \n 1-Si escribes GUARDAR se guardara todo lo hecho hasta el momento." +
                "\n 2-Si escribes PARAR saldras del programa.");
        do {
            temporalString = sc.nextLine();
            if (!temporalString.equals("Parar")){
                if (temporalString.equals("Guardar")){
                    writeFile();
                }else{
                    System.out.println(temporalString);
                    txt += (temporalString +"\n");
                }
            }
        }while (!temporalString.equals("Parar"));


    }

    /**
     * Este metodo se usa para guardar el String con el texto en el archivo.
     */
    private void writeFile(){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(txt);
        } catch (IOException e) {
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

}
