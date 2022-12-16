package ejercicio6;


import java.io.*;

public class Main {
    public static void main(String[] args) {

        /*
         * Crea un obxecto e asígnao a dúas variables, modifica algún atributo y verifica que mudou en ambas variables.
         */
        Tractor tractor1 = new Tractor("Rojo","Elias", 1600);
        Tractor tractor2 = tractor1;
        tractor2.setColor("Azul");

        System.out.println(tractor1);

        /*
         * Clona o obxecto utilizando a serialización, modifica o valor dun atributo deste novo obxecto e verifica que non modificou o orixinal.
         */

        Tractor tractor3 = new Tractor("Morado","Juan", 1800);
        Tractor tractor4 = deserializar(serializar(tractor3));
        tractor4.setColor("Amarillo");
        System.out.println(tractor3);
        System.out.println(tractor4);

        /*
         * Comproba co atributo identificador da clase que se está a utilizar a mesma versión.
         */
        System.out.println(tractor3.getSerialVersionUID());
        System.out.println(tractor4.getSerialVersionUID());

    }

    /**
     * Este metodo serializara un objeto en un array de bytes.
     * Lo primero es invocar 2 objetos, ByteArrayOutputStream, que se encargara de pasar los datos a un array de bytes, y
     * ObjectOutputStream que se encarga de pasar el objeto para serializar.
     * Una vez invocados le diremos al ObjectOutputStream que va a usar el ByteArrayOutputStream, luego usaremos un
     * metodo para escribir el objeto en el ObjectOutputStream y luego pasarlo a un array y devolverlo.
     * Tambien cerraremos los objetos que usamos al principio.
     * @param tractor Objeto que se va a serializar
     * @return conjunto de datos serializados.
     */
    public static byte[] serializar(Tractor tractor){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] myBytes = null;
        try(ObjectOutputStream oss = new ObjectOutputStream(new ByteArrayOutputStream())){
            oos = new ObjectOutputStream(bos);
            oos.writeObject(tractor);
            oos.flush();
            myBytes = bos.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myBytes;
    }

    /**
     * Este metodo deserializara un array de bytes en un objeto, para eso invocaremos dos metodos, el primero sera el encargado
     * de coger el array y pasarlo al Stream, luego usaremos otro metood para pasar eso a un objeto para luego devolverlo.
     * Tambien cerraremos estos objetos.
     * @param myByte conjunto de datos para deserializar.
     * @return Objeto deserializado.
     */
    public static Tractor deserializar(byte[] myByte){
        ByteArrayInputStream bis = new ByteArrayInputStream(myByte);
        ObjectInputStream ois = null;
        Tractor salida = null;
        try {
            ois = new ObjectInputStream(bis);
            salida = (Tractor) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return salida;
    }
}
