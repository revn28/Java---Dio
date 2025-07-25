
import java.util.Scanner;


public class contaTerminal {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem vindo ao Banco do Popular");
           
            //dados predefinidos para teste-----------------
            // String agencia = "123";
            // int conta = 1123;
            // String nome = "Roberto";
            // double saldo = 2.000;
            // String dadoDaConta = mensagemBoasVindas(agencia, conta, nome, saldo);
            
            // if(numAgencia.equals(agencia))
            //    System.out.println(dadoDaConta);
            // else
            //     System.out.println("Sua conta não foi localizada. Tente novamente!");

            // Entrada de dados pelo usuario via terminal------------------------------------
            System.out.println("Digite sua Agencia: ");
            String numAgencia = scanner.next();

            System.out.println("Digite sua Conta: ");
            int numConta = scanner.nextInt();

            System.out.println("Digite seu nome: ");
            String nomeNovo = scanner.next();

            System.out.println("Deposito inicial? : ");
            Double saldoNovo = scanner.nextDouble();

            String novaConta = mensagemBoasVindas(numAgencia, numConta, nomeNovo, saldoNovo);
            System.out.println(novaConta);
            

        }
    
    }

    public static String mensagemBoasVindas (String agencia, int conta, String nome, double saldo){
        return ("Olá "+ nome + ", obrigado por criar uma conta em nosso banco, sua agência é " + agencia + ", conta " + conta + " e seu saldo R$" + saldo + " já está disponível para saque");
    }



}

