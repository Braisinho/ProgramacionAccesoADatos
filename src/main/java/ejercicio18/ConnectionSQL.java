package ejercicio18;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionSQL {
    public static final String URL = "jdbc:mysql://localhost:3306/ejemplo";
    public static final String USER = "root";
    public static final String CLAVE = "abc123.";

    public Connection getConexion(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
}
