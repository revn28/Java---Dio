
import java.util.Scanner;





public class ExercicioFor {
    // Exerci 6 MaiorNumero
    public static void main(String[] args) {
        int maior = Integer.MIN_VALUE;
        try(Scanner scanner = new Scanner(System.in)){
            for (int i = 1; i <= 10; i++) {
                System.out.println("Digite um numero: ");    
                int numero = scanner.nextInt();
                    
                    if(numero > maior)
                        maior = numero;
            }
            System.out.println("O maior numero digitado foi: "+ maior);
        }
    }


    // // Exerci 5 MediaAlunos-----------------------------------------------------------------------------
    // public static void main(String[] args) {
    //     try(Scanner scanner = new Scanner(System.in)){
    //         System.out.println("A turma tem quantos alunos: ");
    //         int turma = scanner.nextInt();
    //             for (int i = 1; i <= turma; i++) {
    //                 System.out.println("Nome: ");
    //                     String nome = scanner.next();
    //                 System.out.println("Nota de Matematica: ");
    //                     double nota1 = scanner.nextDouble();
    //                 System.out.println("Nota de Português: ");
    //                     double nota2 = scanner.nextDouble();
    //                 System.out.println("Nota de História: ");
    //                     double nota3 = scanner.nextDouble();
                    
    //                 double media = (nota1 + nota2 + nota3) /3;
    //                 System.out.printf(nome +" ficou com uma media de: %.2f ", media); // "out.printf" é necessario para especificar %.2f 
                    
    //             }
    //     }
    // }


    // Exerci 4 Par e Impar acionado pelo usuario -------------------------------------------------------
    // public static void main(String[] args) {
    //     try (Scanner scanner = new Scanner(System.in)){
    //         System.out.println("Digite um numero: ");
    //         int numero =  scanner.nextInt();
    //         for (int i = 1; i <= numero; i++){
    //          int impares = (i % 2);
    //             if (impares == 0) 
    //                 System.out.println( i+ " é Par");
    //             else
    //                 System.out.println(i + " é Impar");
    //     }
    //     }



        // Exerci 3 Par -------------------------------------------------------
        // for (int i = 1; i <= 10; i++){
        //      int impares = (i % 2);
        //         if (impares == 0) 
        //             System.out.println( i+ " é Par");
        //         else
        //             System.out.println(i + " é Impar");
        // }
    
        // Exerci 2 impares ------------------------------------------------------
        // for (int i = 1; i <= 10; i++){
        //      int impares = (i % 2);
        //         if (impares != 0) 
        //             System.out.println(i + " é Impar");
        //         else
        //             System.out.println( i+ " é Par");
        // }

        // Exerci 1 -  musica da Xuxa--------------------------------------------
        // try (Scanner scanner = new Scanner(System.in)) {
        //     int patinhoX = 1;
        //     System.out.println("Quantos patinhos a pata tem: ");
        //     int totalPatinho = scanner.nextInt();

        //     for (int patinho = totalPatinho; patinho <= totalPatinho; patinho--) {
        //         int menospatinho =  patinho - patinhoX;
        //         if (menospatinho >= 0)
        //             System.out.println( patinho + " patinhos foram passear\r\n" + //
        //                                 "Além das montanhas\r\n" + //
        //                                 "Para brincar\r\n" + //
        //                                 "A mamãe gritou: Quá, quá, quá, quá\r\n" + //
        //                                 "Mas só " + menospatinho + " patinhos voltaram de lá.");
        //          else{

        //                  System.out.println("A mamãe patinha foi procurar\r\n" + //
        //                  "Além das montanhas\r\n" + //
        //                  "Na beira do mar\r\n" + //
        //                  "A mamãe gritou: Quá, quá, quá, quá\r\n" + //
        //                  "E os "+ totalPatinho + " patinhos voltaram de lá.");
        //                 break;
        //          }
                        
        //     }
        // }
    }

