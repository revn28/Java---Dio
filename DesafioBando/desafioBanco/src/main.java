import contas.Conta;
import contas.contaPoupanca;
import sistema_banco.Banco;
import usuario.Cliente;
import usuario.Gerente;
import usuario.Iusuario;

public class main {
   
    public static void main(String[] args) {
        Banco bancoExemplo = new Banco("Banco Exemplo de Teste");
        
        // 1. Criar clientes
        Cliente neto = new Cliente();
        neto.setNome("Neto");
        neto.setCpf("111.111.111-11");
        neto.setEmail("neto@email.com");
        neto.setId(1);
        neto.setNivelAcesso(1);
        neto.setSenha("senha123"); // Definindo a senha para o cliente

        Cliente ana = new Cliente();
        ana.setNome("Ana");
        ana.setCpf("222.222.222-22");
        ana.setEmail("ana@email.com");
        ana.setId(2);
        ana.setNivelAcesso(1);
        ana.setSenha("senha456"); // Definindo a senha para o cliente

        // 2. Adicionar clientes e gerente ao Banco
        bancoExemplo.adicionarCliente(neto);
        bancoExemplo.adicionarCliente(ana);

        Gerente gerenteJoao = new Gerente(101, "João Gerente", "333.333.333-33", "joao@banco.com", "01/01/1980", "9999-8888", "admin123"); // Gerente com senha
        bancoExemplo.adicionarGerente(gerenteJoao);
        
        System.out.println("\n--- Teste de Login ---");
        // Tentativas de Login
        Iusuario usuarioLogado = bancoExemplo.login("111.111.111-11", "senha123"); // Login de cliente
        if (usuarioLogado instanceof Cliente) {
            Cliente clienteLogado = (Cliente) usuarioLogado;
            System.out.println("Cliente logado: " + clienteLogado.getNome());
        }

        Iusuario gerenteLogado = bancoExemplo.login("333.333.333-33", "admin123"); // Login de gerente
        if (gerenteLogado instanceof Gerente) {
            Gerente gLogado = (Gerente) gerenteLogado;
            System.out.println("Gerente logado: " + gLogado.getNome());
        }

        bancoExemplo.login("111.111.111-11", "senhaIncorreta"); // Login falho
        bancoExemplo.login("999.999.999-99", "qualquer"); // Login falho

        System.out.println("\n--- Criação de Contas por Gerente ---");
        // Gerente cria contas para clientes
        if (gerenteLogado instanceof Gerente) {
            Gerente gLogado = (Gerente) gerenteLogado;

            Conta contaNetoCorrente = bancoExemplo.criarContaParaCliente(gLogado, neto, "corrente");
            Conta contaAnaPoupanca = bancoExemplo.criarContaParaCliente(gLogado, ana, "poupanca");
            Conta contaNetoPoupanca = bancoExemplo.criarContaParaCliente(gLogado, neto, "poupanca");
            
            // Tentar criar com tipo inválido
            bancoExemplo.criarContaParaCliente(gLogado, ana, "investimento");
            // Tentar criar por um cliente (deveria falhar)
            // bacoExemplo.criarContaParaCliente((Gerente)usuarioLogado, neto, "corrente"); // Isso geraria ClassCastException
        
            System.out.println("\n--- Operações de Contas Criadas ---");
            if (contaNetoCorrente != null) {
                contaNetoCorrente.depositar(1500.00);
                contaNetoCorrente.imprimirDados();
            }
            if (contaAnaPoupanca != null) {
                contaAnaPoupanca.depositar(500.00);
                contaAnaPoupanca.imprimirDados();
            }
            if (contaNetoPoupanca != null) {
                contaNetoPoupanca.depositar(250.00);
                contaNetoPoupanca.imprimirDados();
            }

            // Transferência e investimento com as contas criadas
            if (contaNetoCorrente != null && contaAnaPoupanca != null) {
                contaNetoCorrente.transferir(300.50, contaAnaPoupanca);
                System.out.println("\nApós transferência:");
                contaNetoCorrente.imprimirDados();
                contaAnaPoupanca.imprimirDados();
            }

            if (contaNetoCorrente != null && contaNetoPoupanca != null) {
                 System.out.println("\n--- Teste de Investimento ---");
                contaNetoCorrente.investir(200.00, (contaPoupanca) contaNetoPoupanca);
                contaNetoCorrente.imprimirDados();
                contaNetoPoupanca.imprimirDados();
            }
        } else {
            System.out.println("Gerente não logado, não é possível criar contas ou listar.");
        }

        // Listar todas as contas (apenas com gerente logado)
        System.out.println("\n--- Listando todas as contas (Acesso Gerente) ---");
        if (gerenteLogado instanceof Gerente) {
            bancoExemplo.listarContas((Gerente) gerenteLogado);
        }
    }
}