package ejercicio19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
    public static final String URL = "jdbc:mysql://localhost:3306/demodb?&rewriteBatchedStatements=true";
    public static final String USER = "root";
    public static final String CLAVE = "abc123.";

    public static Connection getConexion(){
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
