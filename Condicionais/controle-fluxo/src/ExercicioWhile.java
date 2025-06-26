import java.util.Scanner;

// Classe principal
public class ExercicioWhile {
    public static void main(String[] args) {
        // 1) Exibe 20 vezes a mensagem ----------------------------------------------------------------
        // int repeticao = 1;
        // while (repeticao <= 20) {
        //     System.out.println("Eu gosto de estudar Algoritmos!");
        //     repeticao++;
        // }

        // 2) Soma dos números de 1 a 15 ---------------------------------------------------------------
        // CalculadoraSoma calculadora = new CalculadoraSoma();
        // int soma = calculadora.somarDe1a15();
 
        // 3) Leia o nome do usuário e escreva o nome dele na tela 10 vezes. --------------------------- 
        // RepetirNome repetirNomeUsuario = new RepetirNome();
        // String nome = repetirNomeUsuario.repetir10x();

         // 4) Leia o nome um número do usuário um número N e escreva o nome dele na tela N vezes ------
            // RepetirNome repetirNomeNVezes =  new RepetirNome();
            // String nome = repetirNomeNVezes.repetirXVezes();
    
        // 5) Escreva um algoritmo que leia 10 números do usuário e calcule a soma desses números.
        RepetirNome lernumeroUsuario = new RepetirNome();
        lernumeroUsuario.somaTodosNum();
    
    
        }
}

// Classe para entrada do usuário
class EntradaUsuario {
    // 2) Soma dos números de 1 a 15
    public int somaNumero() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um número: ");
        int numero = scanner.nextInt();
        // scanner.close();  // ← Comentado para evitar erro se reutilizar o Scanner
        return numero;
    }
    // 3) Leia o nome do usuário e escreva o nome dele na tela 10 vezes. 
    public String lerNome() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um nome: ");
        String nome = scanner.next();
        // scanner.close();  // ← Comentado para evitar erro se reutilizar o Scanner
        return nome;
    

    }

    // 4) Leia o nome um número do usuário um número N e escreva o nome dele na tela N vezes ----------------------------------------
    public int numVezes(){
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Digite o numero de vezes que seu nome vai aparecer: ");
        int num = scanner.nextInt();
        return num;
        
    }

    // 5) Escreva um algoritmo que leia 10 números do usuário e calcule a soma desses números.
    public int lerNumero(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um numero: ");
        return scanner.nextInt();
        
    }

// Classe que realiza cálculos
// 2) Soma dos números de 1 a 15
// class CalculadoraSoma {
//     public int somarDe1a15() {
//         EntradaUsuario usuario = new EntradaUsuario();
//         int numero = usuario.somaNumero();
//         int soma = 1;
//         while (soma <= 15) {
//             int resultado = numero + soma;
//             System.out.println(numero + "+" + soma + "=" + resultado);
            
//             // soma += contador;
//              soma++;
//         }

//         return soma;
//     }
// }
}
class RepetirNome{
    // public String repetir10x() {
    //     EntradaUsuario usuario = new EntradaUsuario();
    //     String nome = usuario.lerNome();
    //     int repetir = 1;    
    //     while (repetir <= 10) {
    //         System.out.println("Meu nome é: " + nome + " x " + repetir);
    //         repetir++; 
                
    //         }
    //     return nome;
    //     }
        
    // public String repetirXVezes() {
    //     EntradaUsuario usuario = new EntradaUsuario();
    //     String nome = usuario.lerNome();
    //     int vezes = usuario.numVezes();
    //     int repetir = 1;    
    //     while (repetir <= vezes) {
    //         System.out.println("Meu nome é: " + nome + " x " + repetir);
    //         repetir++; 
                
    //         }
    //     return nome;
    //     }

    public void somaTodosNum(){
        EntradaUsuario lerNum = new EntradaUsuario(); 
        
        int soma = 0;
        int contador = 1;

        while (contador <= 10) {
            int numero = lerNum.lerNumero(); // Lê um número
            soma += numero;           // Adiciona à soma
            contador++;
        }

        System.out.println("A soma de todos os números é: " + soma);
        

    }
   
        

}