
import java.util.Scanner;



public class TryCatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    do{

        try {
            
            System.out.println("Digite seu nome: ");
            String nome =  scanner.next();
            
            System.out.println("Digite seu sobrenome");
            String sobrenome = scanner.next();
            
            System.out.println("Digite sua idade: ");
            int idade = scanner.nextInt();
            
            System.out.println("Digite sua altura: ");
            double altura = scanner.nextDouble();
            
            
            System.out.println("Ola, me chamo "+ nome.toUpperCase() + " " + sobrenome.toUpperCase());
            System.out.println("Tenho " + idade + " anos");
            System.out.println("Minha altura é " + altura + " cm"); 
            
            
        }
        catch (Exception e) {
            System.err.println("Por favor digite numeros para idade e altura");
            scanner.nextLine(); //Limpa a proxima linha
        } 
      
    } while(true);
}


}