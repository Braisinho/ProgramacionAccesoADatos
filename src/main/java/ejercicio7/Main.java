package ejercicio7;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int option = 0;

        File file = new File("F:\\2ºDAM\\Acceso a datos\\Pruebas");

        do {
            try {
                option = menu();
                Menu.menuOptions(option, file);
            }catch (InputMismatchException a){
                System.out.println("Introduce valores numericos.\n");
            }

        }while (option != 9);

    }

    private static int menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n"+
                "1-Listar os arquivos e cartafois.\n" +
                "2-Ver os permisos dun ficheiro.\n" +
                "3-Crear un ficheiro.\n" +
                "4-Crear un directorio.\n" +
                "5-Borrar un ficheiro ou cartafol\n" +
                "6-Renomear un arquivo ou cartafol\n" +
                "7-Ler un ficheiro.\n" +
                "8-Escribir nun ficheiro.\n" +
                "9-Salir");

        return sc.nextInt();
    }
}
