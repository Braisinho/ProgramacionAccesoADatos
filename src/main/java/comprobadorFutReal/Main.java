package comprobadorFutReal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String psw = "23101999BraisFerro+";
        Persona a = null;
        try {
            a = new Persona("Brais","Ferro",simpleDateFormat.parse("23-10-1999"),psw);
            System.out.println(a.getFechaNacimiento().toString());
        } catch (PasswordException | ParseException e) {
            System.out.println(e.getMessage());
        }

    }
}
