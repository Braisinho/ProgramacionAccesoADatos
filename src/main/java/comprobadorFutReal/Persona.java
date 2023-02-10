package comprobadorFutReal;

import java.util.Date;

public class Persona {

    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String password;

    public Persona(String nombre, String apellido, Date fechaNacimiento, String password) throws PasswordException {
        setApellido(apellido);
        setNombre(nombre);
        setFechaNacimiento(fechaNacimiento);
        setPassword(password);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordException {

        boolean mayus = false;
        boolean minus = false;
        boolean number = false;
        boolean simbols = false;

        if (password.length() < 9) throw new PasswordException("El tamaño de la contraseña es demasiado pequeño");
        if (password.contains(fechaNacimiento.toString().split(" ")[5]) || password.contains(fechaNacimiento.toString().split(" ")[2])) throw new PasswordException("No se puede incluir la fecha de nacimineto en la contraseña.");
        if (password.contains(nombre) || password.contains(apellido)) throw new PasswordException("No se puede incluir el nombre o el apellido en la contraseña.");

        for (char c: password.toCharArray()) {
            if (Character.isDigit(c)) number = true;
            if (Character.isLowerCase(c)) minus = true;
            if (Character.isUpperCase(c)) mayus = true;
            if (!Character.isLetterOrDigit(c)) simbols = true;
        }

        if (!number) throw new PasswordException("Se necesita tener como minimo un numero.");
        if (!minus) throw new PasswordException("Se necesita tener como minimo una letra minuscula.");
        if (!mayus) throw new PasswordException("Se necesita tener como minimo una letra mayuscula.");
        if (!simbols) throw new PasswordException("Se necesita tener como minimo un simbolo especial.");

        this.password = password;
    }
}
