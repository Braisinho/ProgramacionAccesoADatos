package ejercicio20;

public class Main {
    public static void main(String[] args) {
        //CreadorBD.createTables();
        //XeradorBD.addDataBatch();
        DAO.consulta3(12);
        ConnectionSQL.closeConecction();

    }
}
