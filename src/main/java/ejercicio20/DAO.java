package ejercicio20;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAO {

    public static void consulta1(){
        String consulta= "SELECT\n" +
                "    u.Nome,\n" +
                "    COUNT(u.Id) AS Numero_Persoaxe\n" +
                "FROM\n" +
                "    persoaxe p\n" +
                "    JOIN usuario u ON u.Id = p.Id_Usuario\n" +
                "GROUP BY\n" +
                "    u.Id\n" +
                "HAVING\n" +
                "    COUNT(u.Id) > 0;";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta); ResultSet rs = statement.executeQuery()) {
            System.out.println(" ___________________________________");

            System.out.println("| Nombre de Usuario | Nº Personajes |");
            while (rs.next()){
                if (rs.getString("u.Nome").length()==11){
                    System.out.println("| "+rs.getString("u.Nome") + "       | " + rs.getInt("Numero_Persoaxe") + "             |");
                }else{
                    System.out.println("| "+rs.getString("u.Nome") + "        | " + rs.getInt("Numero_Persoaxe") +  "             |");
                }
            }
            System.out.println(" ___________________________________");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consulta2(int x){
        String consulta = "select\n" +
                "    u.Nome,\n" +
                "    COUNT(u.Nome) as Numero_Persoaxe\n" +
                "from\n" +
                "    persoaxe p\n" +
                "    join usuario u on p.Id_Usuario = u.Id\n" +
                "where\n" +
                "    Id_Usuario = ?;";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta)) {
            statement.setInt(1,x);
            ResultSet rs = statement.executeQuery();
            System.out.println(" ___________________________________");
            System.out.println("| Nombre de Usuario | Nº Personajes |");
            while (rs.next()){
                if (rs.getString("u.Nome").length()==11){
                    System.out.println("| "+rs.getString("u.Nome") + "       | " + rs.getInt("Numero_Persoaxe") + "             |");
                }else{
                    System.out.println("| "+rs.getString("u.Nome") + "        | " + rs.getInt("Numero_Persoaxe") +  "             |");
                }
            }
            System.out.println(" ___________________________________");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consulta3(int x){
        String consulta = "select\n" +
                "    p.Nome as Nome_Persoaxe,\n" +
                "    p.Id as Numero,\n" +
                "    p.Id_Servidor,\n" +
                "    u.Nome as Nome_Usuario\n" +
                "from\n" +
                "    persoaxe p\n" +
                "    JOIN usuario u ON u.Id = p.Id_Usuario\n" +
                "where\n" +
                "    p.Id_Usuario = ?;";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta)) {
            statement.setInt(1,x);
            ResultSet rs = statement.executeQuery();
            System.out.println(" ___________________________________________________________________________");
            System.out.println("| Nombre do Persoaxe | Nº do Persoaxe | Nº do Servidor | Nombre do Usuario |");
            while (rs.next()){
                if (rs.getString("Nome_Persoaxe").length() == 12){
                    System.out.println("| " +rs.getString("Nome_Persoaxe") + "       |" + rs.getInt("Numero") + "              |" + rs.getInt("Id_Servidor")+
                            "               |" + rs.getString("Nome_Usuario") + "        |");
                }else{
                    System.out.println("| " +rs.getString("Nome_Persoaxe") + "        |" + rs.getInt("Numero") + "               |" + rs.getInt("Id_Servidor")+
                            "               |" + rs.getString("Nome_Usuario") +  "        |");
                }

            }
            System.out.println(" ___________________________________________________________________________");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consulta4(){
        String consulta = "select\n" +
                "    u.Nome as Nome_Usuario,\n" +
                "    s.Nome as Nome_Servidor,\n" +
                "    COUNT(p.Id) as Numero_Personaxes\n" +
                "from\n" +
                "    persoaxe p\n" +
                "    JOIN Servidor s on p.Id_Servidor = s.Id\n" +
                "    JOIN Usuario u on p.Id_Usuario = u.Id\n" +
                "GROUP BY\n" +
                "    s.Nome,\n" +
                "    u.Nome;";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta); ResultSet rs = statement.executeQuery()) {
            while (rs.next()){

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consulta5(){
        String consulta = "select\n" +
                "    s.Nome,\n" +
                "    COUNT(p.Id)\n" +
                "from\n" +
                "    persoaxe p\n" +
                "    JOIN Servidor s on p.Id_Servidor = s.Id\n" +
                "GROUP BY\n" +
                "    s.Nome\n" +
                "order by\n" +
                "    COUNT(p.Id) desc\n" +
                "limit\n" +
                "    ?;";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta); ResultSet rs = statement.executeQuery()) {
            while (rs.next()){

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consulta6(){
        String consulta = "select\n" +
                "    Rexion,\n" +
                "    COUNT(Nome) as Numero_Servidores\n" +
                "from\n" +
                "    servidor\n" +
                "where\n" +
                "    Rexion like 'EUW'\n" +
                "GROUP by\n" +
                "    Rexion;";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta); ResultSet rs = statement.executeQuery()) {
            while (rs.next()){

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consulta7(){
        String consulta = "select\n" +
                "    Rexion,\n" +
                "    COUNT(Nome) as Numero_Servidores\n" +
                "from\n" +
                "    servidor\n" +
                "GROUP by\n" +
                "    Rexion;\n";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta); ResultSet rs = statement.executeQuery()) {
            while (rs.next()){

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consulta8(){
        String consulta = "select\n" +
                "    Nome,\n" +
                "    Alto,\n" +
                "    Ancho\n" +
                "from\n" +
                "    zona\n" +
                "where\n" +
                "    Id_Mapa = ?;";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta); ResultSet rs = statement.executeQuery()) {
            while (rs.next()){

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
