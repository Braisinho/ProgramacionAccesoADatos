package ejercicio18;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ConnectionSQL connectionSQL = new ConnectionSQL();
        Connection con = connectionSQL.getConexion();
        if (con!= null){
            System.out.println("Conexion");
        }
    }
}
