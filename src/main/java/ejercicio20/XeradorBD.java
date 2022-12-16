package ejercicio20;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class XeradorBD {

    public static void addDataBatch(){
        addUsersBatch();
        addServersBatch();
        addMapBatch();
        addZoneBatch();
        addCharacterBatch();
        addServersMapBatch();
    }

    private static void addUsersBatch(){
        String[] uniqueValues = (String[]) createUniqueCodeUsers().toArray();
        for (int i = 0; i < 50; i++) {
            try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement("INSERT into dept VALUES(?,?,?)")) {
                statement.setInt(1,i);
                statement.setString(2, "Usuario :" + i);
                statement.setString(3,uniqueValues[i]);
                statement.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static HashSet<String> createUniqueCodeUsers(){
        HashSet<String> exit = new HashSet<>();
        while (exit.size() != 50){
            int valorEntero = (int) (Math.floor(Math.random()*(1000-9999+1)+9999));
            exit.add(String.valueOf(valorEntero));
        }

        return exit;
    }

    private static void addServersBatch(){

    }

    private static void addMapBatch(){

    }

    private static void addZoneBatch(){

    }

    private static void addCharacterBatch(){

    }

    private static void addServersMapBatch(){

    }

}
