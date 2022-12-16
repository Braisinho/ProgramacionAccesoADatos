package ejercicio11;

import java.io.Serializable;

public class Persona implements Serializable {
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;

    public Persona(){}

    public Persona(String dni, String nombre, String apellido, int edad, String sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    @Override
    public String toString() {
        return this.getDni() + ", " + this.getNombre() + ", "
                + this.getApellido() + ", " + this.getEdad()+ ", "
                + this.getSexo() + ".\n";
    }
}
