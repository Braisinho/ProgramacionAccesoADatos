package ejercicio20;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        while (opc != 3) {
            opc = 0;
            System.out.println("Selecciona alguna de las opciones del menu:" +
                    "\n\t 1 -Xestion da base de datos." +
                    "\n\t 2 -Consultas sobre la base de datos." +
                    "\n\t 3 -Salir.");
                try {
                    opc = sc.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("Introduce solo valores numericos");
                }
            }
        ConnectionSQL.closeConecction();
        }
    }

