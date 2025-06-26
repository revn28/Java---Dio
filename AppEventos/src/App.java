import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static UsuarioDAO usuarioDAO;

    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        int opcao;

        usuarioDAO = new UsuarioDAO();
        usuarioDAO.criarTabela();

        do {
            System.out.println("\n---------Tela de Login/Cadastro---------");
            System.out.println("1 para Login");
            System.out.println("2 para Cadastro");
            System.out.println("0 para Sair");
            System.out.print("Digite sua opção: ");

            try {
                opcao = entrada.nextInt();
                entrada.nextLine(); // Consumir a nova linha pendente
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um NÚMERO (1, 2 ou 0).");
                entrada.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    System.out.println("--- Realizar Login ---");
                    System.out.print("Login (Email): ");
                    String loginDigitado = entrada.nextLine();
                    System.out.print("Senha: ");
                    String senhaDigitada = entrada.nextLine();

                    Cadastro usuarioLogado = usuarioDAO.buscarUsuarioParaLogin(loginDigitado, senhaDigitada);

                    if (usuarioLogado != null) {
                        System.out.println("Login bem-sucedido! Bem-vindo(a)!");
                        // AQUI: Chamamos a TelaInicial, passando o usuário logado e o scanner
                        TelaInicial telaInicial = new TelaInicial(usuarioLogado, entrada);
                        telaInicial.exibirMenuPrincipal(); // Inicia o menu de eventos
                        // Após o logout na TelaInicial, o fluxo retorna para este ponto no App.java
                    } else {
                        System.out.println("Login ou senha incorretos. Tente novamente.");
                    }
                    break;
                case 2:
                    System.out.println("--- Realizar Cadastro ---");
                    Cadastro novoCadastro = new Cadastro("", "", "", "", "", "", "");

                    System.out.print("Digite um Email: ");
                    novoCadastro.email = entrada.nextLine();

                    if (usuarioDAO.verificarEmailExistente(novoCadastro.email)) {
                        System.out.println("Este email já está cadastrado. Por favor, use outro.");
                        break;
                    }

                    System.out.print("Registre uma Senha: ");
                    novoCadastro.senhaCadastro = entrada.nextLine();

                    System.out.print("Nome completo: ");
                    novoCadastro.nomeCompleto = entrada.nextLine();

                    System.out.print("Como deseja ser chamado: ");
                    novoCadastro.apelido = entrada.nextLine();

                    System.out.print("Data de nascimento ('dd/mm/aaaa'): ");
                    novoCadastro.dataNascimento = entrada.nextLine();

                    System.out.print("Digite seu cpf: ");
                    novoCadastro.cpf = entrada.nextLine();

                    System.out.print("Digite seu numero de telefone: ");
                    novoCadastro.telefone = entrada.nextLine();



                    // A chamada para inserirUsuario agora retorna o ID gerado,
                    // mas não precisamos armazená-lo aqui no App, pois o objeto
                    // 'novoCadastro' já não será usado para passar à TelaInicial após o cadastro
                    usuarioDAO.inserirUsuario(novoCadastro);
                    System.out.println("Cadastro realizado com sucesso!");
                    System.out.println("Resumo do cadastro: Email: " + novoCadastro.email + ", Nome: " + novoCadastro.nomeCompleto);

                    break;
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    if (opcao != -1) {
                        System.out.println("Opção inválida. Por favor, digite 1, 2 ou 0.");
                    }
                    break;
            }
        } while (opcao != 0);

        entrada.close();
    }
}