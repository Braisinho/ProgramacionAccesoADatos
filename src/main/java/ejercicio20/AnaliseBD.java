package ejercicio20;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class AnaliseBD {

    //rankServers() que mostre por pantalla os 5 servidores con máis personaxes da forma "O servidor X ten Y personaxes".
    public static void rankServers() {
        String consulta = "select\n" +
                "    s.Nome,\n" +
                "    COUNT(p.Id)\n" +
                "from\n" +
                "    persoaxe p\n" +
                "    join servidor s on p.Id_Servidor = s.Id\n" +
                "GROUP by\n" +
                "    s.Nome\n" +
                "order by \n" +
                "    COUNT(p.Id) desc\n" +
                "limit \n" +
                "    5;  ";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta); ResultSet rs = statement.executeQuery()) {
            while (rs.next()){
                System.out.println("O servidor "+ rs.getString("s.Nome")+" ten " + rs.getInt("COUNT(p.Id)")+ " personaxes");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*listServers() que mostre o nome dos servidores por rexión coa forma:
        Rexión X
             Servidor1
             Servidor2
        Rexión Y
             Servidor3
     */
    public static void listServers() {
        String consulta ="select\n" +
                "    Nome,\n" +
                "    Rexion\n" +
                "from\n" +
                "    Servidor\n" +
                "order by\n" +
                "    Rexion,\n" +
                "    Nome;";

        StringBuilder rexion1 = new StringBuilder("Rexion EUW \n");
        StringBuilder rexion2 = new StringBuilder("Rexion KR \n");
        StringBuilder rexion3 = new StringBuilder("Rexion NA \n");

        try (PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta); ResultSet rs = statement.executeQuery()){
            while (rs.next()){
                if (rs.getString("Rexion").equals("EUW")){
                    rexion1.append("\t").append(rs.getString("Nome")).append(" \n");
                } else if (rs.getString("Rexion").equals("KR")) {
                    rexion2.append("\t").append(rs.getString("Nome")).append(" \n");
                }else{
                    rexion3.append("\t").append(rs.getString("Nome")).append(" \n");
                }
            }
            System.out.println(rexion1);
            System.out.println(rexion2);
            System.out.println(rexion3);
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    /*getUserPJ(id) que mostre por pantalla o número de personaxes dun usuario en concreto, o número de personaxes por servidor e seus nomes da seguinte forma:
        X (Y personaxes)
           Servidor1
                pj1
           Servidor3
                pj2
                pj3
    */

    public static void getUserPJ(int id) {
        HashSet<Integer> server = new HashSet<>();

        String consulta ="select\n" +
                "    Id_Servidor,\n" +
                "    Nome\n" +
                "from\n" +
                "    persoaxe\n" +
                "where \n" +
                "    Id_Usuario = ?";

        try (PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta,TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)){
            statement.setInt(1,id);
            try(ResultSet rs = statement.executeQuery()) {
                rs.last();
                int numPJ = rs.getRow();
                rs.beforeFirst();
                System.out.println("Usuario: " + id + " (" +numPJ + " Personajes)");
                while (rs.next()){
                    if (server.add(rs.getInt("Id_Servidor"))){
                        System.out.println("Servidor : " + rs.getInt("Id_Servidor"));
                    }
                    System.out.println("\t" + rs.getString("Nome"));
                }

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //userPJs() que mostre por pantalla todos os usuarios e o número de personaxes que ten. Mostra 5 por liña co número entre parénteses.
    public static void userPJs(){
        int count = 0;
        String consulta ="select\n" +
                "    u.Nome,\n" +
                "    COUNT(u.Nome) as Nº_de_Persoaxes\n" +
                "from\n" +
                "    persoaxe p \n" +
                "    join usuario u on p.Id_Usuario = u.Id\n" +
                " GROUP by\n" +
                "    u.Nome   \n" +
                "order by\n" +
                "    u.nome;";
        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta); ResultSet rs = statement.executeQuery()) {
            while (rs.next()){
                if (count % 5 == 0 && count != 0){
                    System.out.println();
                }
                System.out.print(rs.getString("u.Nome") + " ("+rs.getInt("Nº_de_Persoaxes")+")\t");
                count++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //areaMap(id) que mostre o área dun mapa cun id en concreto. (área = ancho x alto)
    public static void areaMap(int id){
        int area = 0;
        String consulta ="select \n" +
                "    Ancho, \n" +
                "    Alto\n" +
                "from zona\n" +
                "where Id_Mapa = ?";

        try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta)) {
            statement.setInt(1,id);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    area += rs.getInt("Ancho") * rs.getInt("Alto");
                }
            }
            System.out.println("El area del mapa " + id +" es: " + area);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
