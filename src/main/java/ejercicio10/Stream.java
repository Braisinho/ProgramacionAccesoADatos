package ejercicio10;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Stream {

    /**
     * Este metodo usara el OutputStreamWriter para escribir, para eso lo creamos e inicializamos pasandole como
     * parametros la codificacion UTF-8 y un OutputStrem donde ira la ruta.
     * Luego llamaremos al metodo .write(txt) donde escribiremos en el fichero.
     *
     * @param file ruta del archivo donde vamos a guardar el texto.
     * @param txt texto que guardaremos.
     */
    public static void streamWriter(File file , String txt){
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8);
            out.write(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *Este metodo lo usamemos para leer un archivo, para eso lo primero que necesitamos es un InputStreamReader,
     * lo inicializamos pasandole como parametros la codificacion UTF-8 y un InputStream con la ruta.
     * Una vez tengamos esto, usaremos el metodo read() que nos devolvera el valor en ASCII de cada caracter,
     * que mediante un bucle iremos casteando y mostrando por pantalla.
     *
     * @param file ruta del archivo donde vamos a sacar el texto.
     */
    public static void streamReader(File file ){
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(Files.newInputStream(file.toPath()),StandardCharsets.UTF_8);
            int letter = in.read();
            while (letter != -1){
                System.out.print((char) letter);
                letter = in.read();
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
