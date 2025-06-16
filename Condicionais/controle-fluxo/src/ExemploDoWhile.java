
import java.util.Random;


public class ExemploDoWhile {

    public static void main(String[] args) {
        System.out.println("Discando...");
        do{
            System.out.println("Telefone tocando");
        }while(tocando());

    
        System.out.println("Alô !!!");
    

}
    private static boolean tocando(){
        boolean atendeu = new Random().nextInt(3) == 1; //Random gera um numero aleatorio de 1 a 3 e se o num for = 1 signinfica que ele atendeu.
        System.out.println("Atendeu?" + atendeu);
        //negando o ato de continuar tocando 
        return ! atendeu; // anula a atividade do metodo negando a variavel booleana "atendeu"
    }
}
