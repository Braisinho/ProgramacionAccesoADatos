package ejercicio16;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JSON {

    /**
     * Con este metodo leemos el json
     */
    public static void jSon(){
        JsonObject jSonObject;
        JsonReader reader = null;
        try {
            reader = new JsonReader(new BufferedReader(new InputStreamReader(new FileInputStream(new File("./src/main/java/ejercicio16/data(1).json")), StandardCharsets.UTF_8)));
            jSonObject = JsonParser.parseReader(reader).getAsJsonObject();
            mostrarFichaDeDatos(jSonObject);
            System.out.println();
            descendencia(jSonObject);
            System.out.println();
            historia(jSonObject);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Mostramos los ficheros
     * @param jSonObject
     */
    private static void mostrarFichaDeDatos(JsonObject jSonObject){
        System.out.println("-- Ficha de datos --");
        System.out.println("O DI do individuo: "+jSonObject.get("DI").getAsString());
        System.out.println("O nome do individuo: "+jSonObject.get("Nome").getAsString());
        System.out.println("A organización á que pertence: "+jSonObject.get("Organizacion").getAsString());
    }

    /**
     * Mostramos la descendencia
     * @param jSonObject
     */
    private static void descendencia(JsonObject jSonObject){
       JsonArray array = jSonObject.get("Descendencia").getAsJsonArray();
       for(int i = 0; i<array.size(); i++){
           System.out.println("Fillo " + (i+1) + array.get(i).getAsString());
       }
    }

    /**
     * Mostramos la frase
     * @param jSonObject
     */
    private static void historia(JsonObject jSonObject){
        JsonObject object = jSonObject.get("Titulo").getAsJsonObject();
        JsonArray array = object.get("PalabrasClave").getAsJsonArray();

        System.out.println("Estamos a falar do que se considera o rei numero "+object.get("NumeroRei").getAsString()+" da "+array.get(2).getAsString() + " " + array.get(3).getAsString()+" galega. " +
                "El foi "+array.get(1).getAsString()+" ao mesmo tempo e o seu fillo "+object.get("Sucesor").getAsString()+" foi o que descubriu Irlanda. Morto polos Thuatha-Dé-Dannan " +
                "foi o seu fillo Mil quen os derrocou e apoderouse da illa verde.");
    }
}
