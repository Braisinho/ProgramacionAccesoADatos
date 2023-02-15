package comprobadorFutReal;

import java.time.LocalDate;

public class Persona {

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String password;

    public Persona(String nombre, String apellido, LocalDate fechaNacimiento, String password) throws PasswordException {
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
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
        if (password.contains(String.valueOf(fechaNacimiento.getYear())) || password.contains(String.valueOf(fechaNacimiento.getMonthValue()))) throw new PasswordException("No se puede incluir la fecha de nacimieto en la contraseña.");
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
