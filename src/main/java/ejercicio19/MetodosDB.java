package ejercicio19;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MetodosDB {

    private static final Connection con = ConnectionSQL.getConexion();

    public static void closeConecction(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Inserir un departamento. O método recibirá os argumentos: número, nome e localidade
    public static boolean insertDept(int num, String name, String loc){
        boolean exit = false;

        try(PreparedStatement statement = con.prepareStatement("INSERT into dept VALUES(?,?,?)")) {
            statement.setInt(1,num);
            statement.setString(2,name);
            statement.setString(3,loc);
            statement.executeUpdate();
            exit = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exit;
    }

    // O mesmo que b pero recibindo un só argumento, un obxecto da clase departamento. Será necesario por tanto crear unha clase departamento, con atributos e métodos getter e setter.
    public static boolean insertDept(Dept dept){
        boolean exit = false;
        try(PreparedStatement statement = con.prepareStatement("INSERT into dept VALUES(?,?,?)")) {
            statement.setInt(1,dept.getNum());
            statement.setString(2,dept.getName());
            statement.setString(3,dept.getLoc());
            statement.executeUpdate();
            exit = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exit;
    }

    // Método que devolva un ArrayList de obxectos departamento ante a consulta de todas as columnas de todos os departamentos da táboa dept.
    public static ArrayList<Dept> queryDept(){
        ArrayList<Dept> exit = new ArrayList<>();
        ResultSet rs = null;

        try(PreparedStatement statement = con.prepareStatement("SELECT * from dept")) {
            rs = statement.executeQuery();
            while (rs.next()){
                exit.add(new Dept(rs.getInt("DEPTNO"), rs.getString("DNAME"), rs.getString("LOC")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exit;
    }

    //Método que reciba un número de departamento e devolva unha lista dos empregados (o método debe devolver un List<Emp>) .
    public static List<Emp> queryEmp(int dept){
        List<Emp> exit = new ArrayList<>();
        ResultSet rs = null;

        try(PreparedStatement statement = con.prepareStatement("SELECT * from emp where DEPTNO = ?")) {
            statement.setInt(1,dept);
            rs = statement.executeQuery();
            while (rs.next()){
                exit.add(new Emp(rs.getInt("EMPNO"), rs.getString("ENAME"), rs.getString("JOB"),
                        rs.getInt("MGR"), rs.getDate("HIREDATE"),rs.getDouble("SAL"), rs.getDouble("COMM"),
                        rs.getInt("DEPTNO")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exit;
    }

    //Método que reciba un obxecto e actualice a táboa dept.
    public static boolean updateDept(Dept dept){
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        try(PreparedStatement statement = con.prepareStatement("UPDATE dept SET DEPTNO = ?, DNAME = ?, LOC= ? WHERE DEPTNO = ?")) {
            statement.setInt(1,dept.getNum());
            statement.setString(2,dept.getName());
            statement.setString(3,dept.getLoc());
            statement.setInt(4,dept.getNum());
            int updates  = statement.executeUpdate();
            if (updates == 0){
                System.out.println("No se detecto nigun departamento con el mismo ID, desea introducirlo? \n 1- Si. \n 2- No.");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("si")){
                    insertDept(dept);

                }else if(!confirm.equalsIgnoreCase("no")){
                    System.out.println("El valor introducido por consola no se detecta, se finaliza el programa por seguridad");
                }

            }else{
                exit = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exit;
    }

    //Método que reciba un número de departamento e o dea de baixa.
    public static boolean removeDept(int dept){
        boolean exit = false;

        try(PreparedStatement statement = con.prepareStatement("DELETE FROM dept WHERE DEPTNO = ? ")) {
            statement.setInt(1,dept);
            int deletes = statement.executeUpdate();
            if (deletes != 0){
                exit = true;
            }
        } catch (SQLException e) {
            System.err.println("Este departamento no se puede eliminar pues hay trabajadores asociados a este.");
        }
        return exit;
    }

    //Coma o anterior apartado pero devolvendo o número de filas afectadas.
    public static int removeDeptRows(int dept){
        int exit = 0;
        try(PreparedStatement statement = con.prepareStatement("DELETE FROM dept WHERE DEPTNO = ?")) {
            statement.setInt(1,dept);
            exit = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Este departamento no se puede eliminar pues hay trabajadores asociados a este.");
        }
        return exit;
    }

    //(Exercicio de indagación) Método que reciba o obxecto departamento (primeiro argumento) e actualice a súa localidade (segundo argumento do método). Utilizade o procedemento alamcenado do ficheiro adxunto.
    public static boolean updateStoredProcedures(Dept dept, String loc){
        boolean exit = false;
        try (CallableStatement cst = con.prepareCall("{call actualizaDept (?,?)}")) {
            cst.setInt(1, dept.getNum());
            cst.setString(2, loc);
            cst.executeUpdate();
            exit = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  exit;
    }

    //(Exercicio de indagación) Método que reciba un número de departamento e devolva un obxecto cos datos. Utilizade o procedemento alamcenado do ficheiro adxunto.
    public static Dept consultDept(int id){
        Dept exit = null;
        ResultSet rs = null;
        try (CallableStatement cst = con.prepareCall("{call consultaDepar (?,?,?)}")) {
            cst.setInt(1, id);
            cst.registerOutParameter(2, Types.VARCHAR);
            cst.registerOutParameter(3, Types.VARCHAR);
            cst.execute();

            exit = new Dept(id,cst.getString(2), cst.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exit;
    }

    //Método que reciba unha cantidade e un número de departamento e incremente o soldo de todos os empregados dese departamento nesa cantidade. A actualización realizarase cunha transacción.
    public static int updateSalDept(int sal, int id){
        int exit = 0;
        try(PreparedStatement statement = con.prepareStatement("UPDATE emp SET SAL = SAL + ? WHERE DEPTNO = ?")){
            con.setAutoCommit(false);
            statement.setInt(1,sal);
            statement.setInt(2,id);
            exit = statement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exit;
    }
}
