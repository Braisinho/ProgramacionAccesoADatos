package ejercicio17;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        File file = new File("./src/main/java/ejercicio17/data2.json");
        Persona persona1 = ficheroObjeto(file);
        System.out.println(persona1.toString());
        persona1.setIdade(150);
        persona1.setNome("ABC");
        persona1.setDescendencia(1,"Hola");
        System.out.println(persona1.toString());
        persona1.save(file);
    }

    /**
     * Este metodo se encarga de leer el archivo JSOn y construir un objeto
     * @param file ruta del archivo
     * @return objeto
     */
    public static Persona ficheroObjeto(File file){
        Persona persona = null;
        JsonReader reader = null;
        try {
            reader = new JsonReader(new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)));
            persona = new Gson().fromJson(reader, Persona.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return persona;
    }

}
