package ejercicio6.ejemploProfe;

import java.io.Serializable;


/**
 *
 * @author Odilo
 */
public class Empregado implements Serializable{//Importante que implemente a interfaz Serializable
      
    //Constantes
    private static final long serialVersionUID = -253794827362784L; //Evita confusións entre versións cando se envia o obxecto por unha rede
    private final static double SALARIO_DEF=600;
     
    //Atributos
    private String nome;
    private String apelido;
    private int idade;
    private double salario;
     
    
    //Construtores
    public Empregado(){
        this ("", "", 0, SALARIO_DEF);
    }
     
    public Empregado(String nome, String apelido){
        this (nome, apelido, 0, SALARIO_DEF);
    }
     
 
    public Empregado (String nome, String apelido, int idade){
        this (nome, apelido, idade, SALARIO_DEF);
    }

    public Empregado(String nome, String apelido, int idade, double salario){
        this.nome=nome;
        this.apelido=apelido;
        this.idade=idade;
        this.salario=salario;
    }
    
    
    //Metodos getters & setters
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNome() {
        return nome;
    }
 
    public void setNome(String nombre) {
        this.nome = nombre;
    }
     
    public int getIdade() {
        return idade;
    }
 
    public void setIdade(int idade) {
        this.idade = idade;
    }
 
    public double getSalario() {
        return salario;
    }
     
    public  void setSalario(double salario) {
        this.salario = salario;
    }
     
    public String getApellido() {
        return apelido;
    }
    
    //Métodos auxiliares
    public boolean plus (double salarioPlus){
         
        boolean aumento=false;
        if (idade>40){
            salario+=salarioPlus;
            aumento=true;
        }
        return aumento;
    }
     
    public boolean equals (Empregado a){
         
        if(a.getNome().equals(nome) && a.getApellido().equals(apelido)){
            return true;
        }else{
            return false;
        }
    }
     
    public int compareTo(Empregado a){
             
            int estado=-1;
            if(this.idade==a.getIdade()){
                //Os obxectos son iguais
                estado=0;
            }else if(this.idade>a.getIdade()){
                //O obxecto 1 é maior que o pasado por parámetro
                estado=1;
            }
            return estado;
         
    }
     
    public String toString (){
        String mensaje="O empregado chámase "+nome+" "+apelido+" con "+idade+" anos " +
                "e un salario de "+salario+". UID: " +serialVersionUID;
        return mensaje;
    }
 
    
}

