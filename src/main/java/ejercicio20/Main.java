package ejercicio20;

public class Main {
    public static void main(String[] args) {
        //CreadorBD.createTables();
        //XeradorBD.addDataBatch();
        AnaliseBD.getUserPJ(5);
        ConnectionSQL.closeConecction();

    }
}
