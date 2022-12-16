/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio6.ejemploProfe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Odilo
 */
public class Serializacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creamos el objeto
        Empregado empleado1=new Empregado("Manolo", "Suarez", 56, 1000);
        
        //Creamos o Stream de saída, o fluxo de datos que nos permite transferir o noso objecto dende o nosso programa hacia fora
        //(neste caso encamiña o fluxo de datos cara un ficheiro)
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("D:\\Documentos\\Material docente e laboral\\DAM\\AD\\empleados.dat"))){
            //Crea a sucesión de bytes para incorporala ao fluxo de datos do ObjectOuputStreemantes creado
            oos.writeObject(empleado1);
            
            oos.close();
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        
        //Creamos o Stream de entrada
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\Documentos\\Material docente e laboral\\DAM\\AD\\empleados.dat"))){
            //Recupera a sucesión de bytes do ficheiros qie casteamos ao nosso tipode obxecto (devolve un Object)
            Empregado empregado_recuperado=(Empregado)ois.readObject();
            System.out.println(empregado_recuperado);

        }catch(ClassNotFoundException | IOException e ){
            System.out.println(e.getMessage());
        }
        
    }
    
}
