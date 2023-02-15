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

        if (password.length() < 9) throw new PasswordException("El tamaño de la contraseña es demasiado pequeño");
        if (password.contains(String.valueOf(fechaNacimiento.getYear())) || password.contains(String.valueOf(fechaNacimiento.getMonthValue())) || password.contains(fechaNacimiento.getMonth().toString())) throw new PasswordException("No se puede incluir la fecha de nacimieto en la contraseña.");
        if (password.contains(nombre) || password.contains(apellido)) throw new PasswordException("No se puede incluir el nombre o el apellido en la contraseña.");
        if (!password.matches(".*\\d+.*")) throw new PasswordException("Se necesita tener como minimo un numero.");
        if (!password.matches(".*[a-z].*")) throw new PasswordException("Se necesita tener como minimo una letra minuscula.");
        if (!password.matches(".*[A-Z].*"))throw new PasswordException("Se necesita tener como minimo una letra mayuscula.");
        if (!password.matches(".*[^a-zA-Z0-9].*")) throw new PasswordException("Se necesita tener como minimo un simbolo especial.");

        this.password = password;
    }
}
