package ejercicio20;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

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
                    rexion1.append("\t"+rs.getString("Nome") + " \n");
                } else if (rs.getString("Rexion").equals("KR")) {
                    rexion2.append("\t"+rs.getString("Nome") + " \n");
                }else{
                    rexion3.append("\t"+rs.getString("Nome") + " \n");
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
        ArrayList<Integer> servidores = new ArrayList<>();
        ArrayList<String> perxonaxes = new ArrayList<>();
        HashSet<Integer> sett = new HashSet<>();

        int count = 0;
        String consulta ="select\n" +
                "    Id_Servidor,\n" +
                "    Nome\n" +
                "from\n" +
                "    persoaxe\n" +
                "where \n" +
                "    Id_Usuario = ?";

        try (PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(consulta)){
            statement.setInt(1,id);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    count++;
                    servidores.add(rs.getInt("Id_Servidor"));
                    perxonaxes.add(rs.getString("Nome"));
                }

                System.out.println("Usuario " + id + " ("+count+" personaxes)");

                for (int i = 0; i < servidores.size(); i++) {
                    if (sett.add(servidores.get(i))){
                        System.out.println("\tServidor " + servidores.get(i));
                    }
                    System.out.println("\t\t" + perxonaxes.get(i));
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //userPJs() que mostre por pantalla todos os usuarios e o número de personaxes que ten. Mostra 5 por liña co número entre parénteses.
    public static void userPJs(){

    }

    //areaMap(id) que mostre o área dun mapa cun id en concreto. (área = ancho x alto)
    public static void areaMap(int id){

    }
}
