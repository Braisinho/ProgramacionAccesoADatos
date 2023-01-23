package ejercicio20;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        while (opc != 3) {
            System.out.println("Selecciona alguna de las opciones del menu:" +
                    "\n\t 1 -Xestion da base de datos." +
                    "\n\t 2 -Consultas sobre la base de datos." +
                    "\n\t 3 -Salir.");
                try {
                    opc = sc.nextInt();
                    switch (opc){
                        case 1 -> menuXestion();
                        case 2 -> menuConsultas();
                        case 3 -> System.exit(0);
                        default -> System.out.println("No existe ninguna opcion con ese numero");
                    }

                }catch (InputMismatchException e){
                    System.out.println("Introduce solo valores numericos");
                    sc.nextLine();
                }
            }
        ConnectionSQL.closeConecction();
    }

    private static void menuXestion(){
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        while (opc != 3) {
            System.out.println("Selecciona alguna de las opciones del menu:" +
                    "\n\t 1 -Crear las tablas." +
                    "\n\t 2 -Insertar datos en las tablas." +
                    "\n\t 3 -Salir.");
            try {
                opc = sc.nextInt();
                switch (opc){
                    case 1 -> {
                        try {
                            CreadorBD.createTables();
                        } catch (SQLException e) {
                            System.out.println("Las tablas ya estan creadas.");
                        }
                    }
                    case 2 -> {
                        try {
                            XeradorBD.addDataBatch();
                        } catch (SQLException e) {
                            System.out.println("Los datos ya estan introducidos en el servidor.");
                        }
                    }
                    case 3 -> System.exit(0);
                    default -> System.out.println("No existe ninguna opcion con ese numero");
                }

            }catch (InputMismatchException e){
                System.out.println("Introduce solo valores numericos");
                sc.nextLine();
            }
        }
    }

    private static void menuConsultas(){
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        while (opc != 3) {
            System.out.println("Selecciona alguna de las opciones del menu:" +
                    "\n\t 1 -Consultas DAO." +
                    "\n\t 2 -Analise BD." +
                    "\n\t 3 -Salir.");
            try {
                opc = sc.nextInt();
                switch (opc){
                    case 1 -> menuConsultasDAO();
                    case 2 -> menuConsultasAnalise();
                    case 3 -> System.exit(0);
                    default -> System.out.println("No existe ninguna opcion con ese numero");
                }

            }catch (InputMismatchException e){
                System.out.println("Introduce solo valores numericos");
                sc.nextLine();
            }
        }
    }

    private static void menuConsultasDAO(){
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        while (opc != 9) {
            System.out.println("Selecciona alguna de las opciones del menu:");
            System.out.println("\t1- O número de personaxes por usuario, devolvendo o nome de usuario e o número de personaxes sempre que o número sexa igual ou maior que 1.\n" +
                    "\t2- O número de personaxes dun usuario X, devolvendo seu nome e número.\n" +
                    "\t3- Os personaxes dun usuario X, devolvendo o nome do usuario, de cada personaxe e o servidor no que están.\n" +
                    "\t4- O número de personaxes de cada usuario en cada servidor. Devolvendo o nome de usuario, número de personaxes e nome de servidor.\n" +
                    "\t5- Os X servidores con máis personaxes ordenados de maior a menor, devolvendo o nome e o número. X é o parámetro que determina o número a delimitar, por exemplo os 3 con máis.\n" +
                    "\t6- O número de servidores de X rexión.\n" +
                    "\t7- O número de servidores de cada rexión.\n" +
                    "\t8- As zonas dun mapa con id X, devolvendo o nome da zona, o alto e o ancho.\n"+
                    "\t9- Salir.");
            try {
                opc = sc.nextInt();
                switch (opc){
                    case 1 -> {
                        DAO.consulta1();
                    }
                    case 2 -> {
                        System.out.println("Introduce el numero del usuario:");
                        DAO.consulta2(sc.nextInt());
                    }
                    case 3 -> {
                        System.out.println("Introduce el numero del usuario:");
                        DAO.consulta3(sc.nextInt());
                    }
                    case 4 -> {
                        DAO.consulta4();
                    }
                    case 5 -> {
                        System.out.println("Introduce el numero del servidor:");
                        DAO.consulta5(sc.nextInt());
                    }
                    case 6 -> {
                        System.out.println("Introduce alguna de estas regiones: \n EUW, NA, KR");
                        DAO.consulta6(sc.next());
                    }
                    case 7 -> {
                        DAO.consulta7();
                    }
                    case 8 -> {
                        System.out.println("Introduce el ID del mapa.");
                        DAO.consulta8(sc.nextInt());
                    }
                    case 9 -> System.exit(0);
                    default -> System.out.println("No existe ninguna opcion con ese numero");
                }

            }catch (InputMismatchException e){
                System.out.println("Introduce solo valores numericos");
                sc.nextLine();
            }
        }
    }

    private static void menuConsultasAnalise(){
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        while (opc != 6) {
            System.out.println("Selecciona alguna de las opciones del menu:" +
                    "\n\t 1 - Mostrar por pantalla os 5 servidores con máis personaxes da forma \"O servidor X ten Y personaxes\".." +
                    "\n\t 2 - Mostrar o nome dos servidores por rexión coa forma:\n" +
                    "\t\t\tRexión X\n" +
                    "     \t\t\tServidor1\n" +
                    "     \t\t\tServidor2\n" +
                    "\t\t\tRexión Y\n" +
                    "     \t\t\tServidor3" +
                    "\n\t 3 - Mostrar por pantalla o número de personaxes dun usuario en concreto, o número de personaxes por servidor e seus nomes da seguinte forma:\n" +
                    "\t\t\tX (Y personaxes)\n" +
                    "   \t\t\tServidor1\n" +
                    "        \t\t\tpj1\n" +
                    "   \t\t\tServidor3\n" +
                    "       \t\t\tpj2\n" +
                    "       \t\t\tpj3" +
                    "\n\t 4 - Mostrar por pantalla todos os usuarios e o número de personaxes que ten. Mostra 5 por liña co número entre parénteses."+
                    "\n\t 5 - Mostrar o área dun mapa cun id en concreto. (área = ancho x alto)"+
                    "\n\t 6 - Salir.");
            try {
                opc = sc.nextInt();
                switch (opc){
                    case 1 -> AnaliseBD.rankServers();
                    case 2 -> AnaliseBD.listServers();
                    case 3 -> {
                        System.out.println("Introduce el numero de usuario:");
                        AnaliseBD.getUserPJ(sc.nextInt());
                    }
                    case 4 -> {
                        AnaliseBD.userPJs();
                    }
                    case 5 -> {
                        System.out.println("Introduce el numero del mapa:");
                    AnaliseBD.areaMap(sc.nextInt());
                    }
                    case 6 -> System.exit(0);
                    default -> System.out.println("No existe ninguna opcion con ese numero");
                }

            }catch (InputMismatchException e){
                System.out.println("Introduce solo valores numericos");
                sc.nextLine();
            }
        }
    }
}

