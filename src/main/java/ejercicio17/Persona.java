package ejercicio17;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Persona {

    private String nome;
    private int idade;
    private ArrayList<String> descendencia;

    public Persona(String nome, int idade, ArrayList<String> descendencia) {
        this.nome = nome;
        this.idade = idade;
        this.descendencia = descendencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public ArrayList<String> getDescendencia() {
        return descendencia;
    }

    public void setDescendencia(ArrayList<String> decendencia) {
        this.descendencia = decendencia;
    }

    @Override
    public String toString() {
        String salidaPersona = "";

        if (this.nome != null){
            salidaPersona = salidaPersona + "nome: " + this.nome + "\n";
        }
        salidaPersona = salidaPersona + "idade: " + this.idade + "\n";
        if (this.descendencia != null){
            for (String s : descendencia) {
                salidaPersona = salidaPersona + "  " + s + "\n";
            }
        }
        return salidaPersona;
    }

    /**
     * Este metodo se encarga de guardar nuestro objeto en formato JSON
     * @param file ruta donde se guardara
     * @return true si se guarda, false en caso de excepcion
     */
    public boolean save(File file){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String datos = gson.toJson(this);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(datos);
            return true;
        } catch (IOException e) {
            return false;
        }finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Este metodo se encarga de cambiar los valores de la descendencia
     * @param pos posicion del hijo
     * @param name nombre del hijo
     */
    public void setDescendencia(int pos, String name){
        this.descendencia.set(pos, name);
    }
}
