package ejercicio20;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreadorBD {

    public static void createTables(){
        ArrayList<String> tables = createStringsTables();
        for (String table : tables) {
            try (PreparedStatement statement = ConnectionSQL.CONN.prepareStatement(table)) {
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<String> createStringsTables(){
        String usuario ="create table Usuario(\n" +
                "    Id char(9),\n" +
                "    Nome char(100),\n" +
                "    CodigoUnico char(4) UNIQUE,\n" +
                "    primary key (Id)\n" +
                ");";
        String servidor ="create table Servidor(\n" +
                "    Id char(9),\n" +
                "    Nome char(100),\n" +
                "    Rexion char(200),\n" +
                "    primary key (Id)\n" +
                ");";
        String mapa ="create table Mapa(\n" +
                "    Id char(9),\n" +
                "    Nome char(100),\n" +
                "    Dificultad int,\n" +
                "    primary key (Id)\n" +
                ");";
        String servidor_mapa ="create table Servidor_Mapa(\n" +
                "    Id_Servidor char(9),\n" +
                "    Id_Mapa char(9),\n" +
                "    foreign key (Id_Mapa) references Mapa(id)\n" +
                "        on update cascade\n" +
                "        on delete cascade,\n" +
                "    foreign key (Id_Servidor) references Servidor(id)\n" +
                "        on update cascade\n" +
                "        on delete cascade,\n" +
                "    primary key (Id_Servidor,Id_Mapa)\n" +
                ");";
        String persoaxe ="create table Persoaxe(\n" +
                "    Id char(9),\n" +
                "    Nome char(100),\n" +
                "    Id_Usuario char(9),\n" +
                "    Id_Servidor char(9),\n" +
                "    foreign key (Id_Usuario) references Usuario(id)\n" +
                "        on update cascade\n" +
                "        on delete cascade,\n" +
                "    foreign key (Id_Servidor) references Servidor(id)\n" +
                "        on update cascade\n" +
                "        on delete cascade,\n" +
                "    primary key (Id)\n" +
                ");";
        String zona ="create table Zona(\n" +
                "    Id char(9),\n" +
                "    Nome char(100),\n" +
                "    Ancho int,\n" +
                "    Alto int,\n" +
                "    Id_Mapa char(9),\n" +
                "    foreign key (Id_Mapa) references Mapa(id)\n" +
                "        on update cascade\n" +
                "        on delete cascade,\n" +
                "    primary key (Id)\n" +
                ");";

        ArrayList<String> tables = new ArrayList<>();
        tables.add(usuario);
        tables.add(servidor);
        tables.add(mapa);
        tables.add(servidor_mapa);
        tables.add(persoaxe);
        tables.add(zona);
        return tables;
    }


}
