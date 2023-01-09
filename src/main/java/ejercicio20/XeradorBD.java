package ejercicio20;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class XeradorBD {

    private final static int NUM_SERVERS = 10;
    private final static int NUM_USERS = 50;
    private final static int NUM_CHARACTER = 100;
    private final static int NUM_MAPS = 20;
    private final static int MAX_NUM_ZONE = 5;

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
        ArrayList<String> uniqueValues = new ArrayList<>(createUniqueCodeUsers());
        PreparedStatement statement = null;
        try {
            statement = ConnectionSQL.CONN.prepareStatement("INSERT into Usuario VALUES(?,?,?)");
            for (int i = 0; i < NUM_USERS; i++) {
                try {
                    statement.setString(1,String.valueOf(i));
                    statement.setString(2, "Usuario :" + i);
                    statement.setString(3,uniqueValues.get(i));
                    statement.addBatch();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addServersBatch(){
        PreparedStatement statement = null;
        try {
            statement  = ConnectionSQL.CONN.prepareStatement("INSERT into Servidor VALUES(?,?,?)");
            for (int i = 0; i < NUM_SERVERS; i++) {
                try {
                    statement.setString(1,String.valueOf(i));
                    statement.setString(2, "Servidor :" + i);
                    statement.setString(3,regions[(int)(Math.random()*3)]);
                    statement.addBatch();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            statement.executeBatch();
            statement.close();
            ConnectionSQL.CONN.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void addMapBatch(){
        PreparedStatement statement = null;
        try {
            statement = ConnectionSQL.CONN.prepareStatement("INSERT into Mapa VALUES(?,?,?)");
            for (int i = 0; i < NUM_MAPS; i++) {
                try {
                    statement.setString(1,String.valueOf(i));
                    statement.setString(2, "Mapa :" + i);
                    statement.setInt(3,(int)(Math.random()*9+1));
                    statement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            statement.executeBatch();
            statement.close();
            ConnectionSQL.CONN.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addZoneBatch(){
        PreparedStatement statement = null;
        try {
            statement = ConnectionSQL.CONN.prepareStatement("INSERT into Zona VALUES(?,?,?,?,?)");
            int count = 0;
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < (int)(Math.random()*MAX_NUM_ZONE+1); j++) {
                    try {
                        statement.setString(1, String.valueOf(count));
                        statement.setString(2, "Zona: " + count);
                        statement.setInt(3,(int)(Math.random()*250+1));
                        statement.setInt(4,(int)(Math.random()*250+1));
                        statement.setString(5, String.valueOf(i));
                        statement.addBatch();
                        count++;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            statement.executeBatch();
            statement.close();
            ConnectionSQL.CONN.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addCharacterBatch(){
        PreparedStatement statement = null;
        try {
            statement = ConnectionSQL.CONN.prepareStatement("INSERT into Persoaxe VALUES(?,?,?,?)");
            for (int i = 0; i < NUM_CHARACTER; i++) {
                statement.setString(1, String.valueOf(i));
                statement.setString(2, "Persoaxe: " + i);
                statement.setString(3,String.valueOf((int)(Math.random()*50)));
                statement.setString(4,String.valueOf((int)(Math.random()*10)));
                statement.addBatch();
            }
            statement.executeBatch();
            statement.close();
            ConnectionSQL.CONN.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addServersMapBatch(){
        PreparedStatement statement = null;
        try {
            statement = ConnectionSQL.CONN.prepareStatement("INSERT into Servidor_Mapa VALUES(?,?)");
            for (int i = 0; i < NUM_MAPS; i++) {
                statement.setString(1,String.valueOf((int)(Math.random()*NUM_SERVERS)));
                statement.setString(2,String.valueOf(i));
                statement.addBatch();
            }
            statement.executeBatch();
            statement.close();
            ConnectionSQL.CONN.commit();
        } catch (SQLException e) {
            e.printStackTrace();
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

}
