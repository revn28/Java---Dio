package edu.roberto.segundasemana;


public class BoletimEstudantil {

    public static void main(String[] args) {
        int media = 8;
        if(media < 6 )
            System.out.println("Reprovado");
        else if(media == 6 )
            System.out.println("Prova Final");    
        else
            System.out.println("Aprovado");
    }
}
