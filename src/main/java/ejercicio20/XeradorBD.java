package ejercicio20;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

public class XeradorBD {

    private static String[] regions = new String[]{"EUW", "NA", "KR"};

    public static void addDataBatch(){
        try {
            ConnectionSQL.CONN.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addUsersBatch();
        addServersBatch();
        addMapBatch();
        addZoneBatch();
        addCharacterBatch();
        addServersMapBatch();
    }

    private static void addUsersBatch(){
        String[] uniqueValues = (String[]) createUniqueCodeUsers().toArray();
        PreparedStatement statement = null;
        for (int i = 0; i < 50; i++) {
            try {
                statement = ConnectionSQL.CONN.prepareStatement("INSERT into Usuario VALUES(?,?,?)");
                statement.setString(1,String.valueOf(i));
                statement.setString(2, "Usuario :" + i);
                statement.setString(3,uniqueValues[i]);
                statement.addBatch();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if (statement != null) {
                statement.executeBatch();
            }
            ConnectionSQL.CONN.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void addServersBatch(){
        PreparedStatement statement = null;
        for (int i = 0; i < 10; i++) {
            try {
                statement = ConnectionSQL.CONN.prepareStatement("INSERT into Servidor VALUES(?,?,?)");
                statement.setString(1,String.valueOf(i));
                statement.setString(2, "Servidor :" + i);
                statement.setString(3,regions[(int)(Math.random()*3)]);
                statement.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if (statement != null) {
                statement.executeBatch();
            }
            ConnectionSQL.CONN.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addMapBatch(){
        PreparedStatement statement = null;
        for (int i = 0; i < 20; i++) {
            try {
                statement = ConnectionSQL.CONN.prepareStatement("INSERT into Mapa VALUES(?,?,?)");
                statement.setString(1,String.valueOf(i));
                statement.setString(2, "Mapa :" + i);
                statement.setInt(3,(int)(Math.random()*9+1));
                statement.addBatch();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if (statement != null) {
                statement.executeBatch();
            }
            ConnectionSQL.CONN.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addZoneBatch(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < (int)(Math.random()*5+1); j++) {
                try(PreparedStatement statement = ConnectionSQL.CONN.prepareStatement("INSERT into Zona VALUES(?,?,?,?,?)")) {

                    statement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static void addCharacterBatch(){

    }

    private static void addServersMapBatch(){

    }

    private static HashSet<String> createUniqueCodeUsers(){
        HashSet<String> exit = new HashSet<>();
        while (exit.size() != 50){
            int valorEntero = (int) (Math.floor(Math.random()*(1000-9999+1)+9999));
            exit.add(String.valueOf(valorEntero));
        }
        return exit;
    }

}
