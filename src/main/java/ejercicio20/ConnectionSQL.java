package ejercicio20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
    public static final String URL = "jdbc:mysql://localhost:3306/juegoonline?&rewriteBatchedStatements=true";
    public static final String USER = "root";
    public static final String CLAVE = "abc123.";

    private static Connection getConexion(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
            con.setAutoCommit(false);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }

    public static final Connection CONN = getConexion();

    public static void closeConecction(){
        try {
            if (CONN!= null){
                CONN.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
