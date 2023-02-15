package comprobadorFutReal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //String pattern = "dd-MM-yyyy";
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        LocalDate fecha = LocalDate.of(2023, 10, 23);
        String psw = "123456789100aAr+";
        String psw2 = "23101999BraisFerro+";
        Persona a = null;
        try {
           a = new Persona("Brais","Ferro",fecha,psw);
            System.out.println(a.getFechaNacimiento().getMonth().toString());
       } catch (PasswordException e) {
           System.out.println(e.getMessage());
       }


        //String password = "myPassword";
        //String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());



    // Verificar la contraseña
       // String enteredPassword = "myPassword";
       // if (BCrypt.checkpw(enteredPassword, encryptedPassword)) {
       //     System.out.println("Contraseña correcta");
       // } else {
       //     System.out.println("Contraseña incorrecta");
       // }

    }
}
