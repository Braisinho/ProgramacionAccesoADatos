package ejercicio10;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Para el flujo de entrada/salida de datos sin búfer, cada solicitud de lectura o escritura es manejada directamente por el
 * sistema operativo. Esto puede hacer que un programa sea mucho menos eficiente, ya que cada
 * solicitud de este tipo a menudo desencadena el acceso al disco, la actividad de la red o alguna otra operación que es relativamente costosa.
 */
public class Buffer {

    /**
     * Para escribir lo primero que vamos a hacer es crear un BufferedWriter, luego lo inicializamos pasandole un
     * OutputStreamWriter, el cual creamos de forma anonima para no tener que cerrarlo en el finally, deltro de este le pasaremos un
     * OutputStream con el path de la ruta y tb lo configuraremos como UTF-8.
     * Luego una vez inicializado, usaremos el metodo .write(txt) para escribir el fichero.
     *
     * @param file ruta del archivo donde vamos a guardar el texto.
     * @param txt texto que guardaremos.
     */
    public static void bufferWriter(File file , String txt){
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8));
            out.write(txt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
     * Para leer un archivo con Buffer, lo primero es crear un objeto BufferedReader,
     * luego lo inicializamos pasandole como parametro un FileReader, el cual llevara consigo el archivo donde queremos sacar el texto.
     * Una vez tenemos esto, crearemos un String donde guardaremos las lineas de texto y las mostraremos por pantalla
     * dentro de un bucle que finalizara cuando no tengamos otra linea.
     *
     * @param file ruta del archivo donde vamos a sacar el texto.
     */
    public static void bufferReader(File file ){
        BufferedReader inp = null;

        try {
            String line;
            inp = new BufferedReader(new FileReader(file));
            while ((line = inp.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inp != null) {
                    inp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
