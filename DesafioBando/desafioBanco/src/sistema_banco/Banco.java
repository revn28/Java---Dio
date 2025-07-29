package sistema_banco;

import contas.Conta;
import contas.contaCorrente;
import contas.contaPoupanca;
import java.util.ArrayList;
import java.util.List;
import usuario.Cliente;
import usuario.Gerente;
import usuario.Iusuario; 

public class Banco {
    private String nomeBanco;
    private List<Conta> contas;
    private List<Cliente> clientes; 
    private List<Gerente> gerentes; 


    public Banco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
        this.contas = new ArrayList<>();
        this.clientes = new ArrayList<>(); 
        this.gerentes = new ArrayList<>(); 
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public List<Conta> getContas() {
        return contas;
    }
    
    public void adicionarConta(Conta conta) {
        if (conta != null) {
            this.contas.add(conta);
            System.out.println(String.format("Conta %d adicionada ao banco %s.", conta.getconta(), this.nomeBanco));
        } else {
            System.out.println("Não é possível adicionar uma conta nula.");
        }
    }

    // Método para adicionar um cliente ao banco
    public void adicionarCliente(Cliente cliente) {
        if (cliente != null && !clientes.contains(cliente)) { // Evitar clientes duplicados
            this.clientes.add(cliente);
            System.out.println(String.format("Cliente %s (%s) adicionado ao banco %s.", cliente.getNome(), cliente.getCpf(), this.nomeBanco));
        } else {
            System.out.println("Não é possível adicionar cliente nulo ou duplicado.");
        }
    }

    // Método para adicionar um gerente ao banco
    public void adicionarGerente(Gerente gerente) {
        if (gerente != null && !gerentes.contains(gerente)) { // Evitar gerentes duplicados
            this.gerentes.add(gerente);
            System.out.println(String.format("Gerente %s (%s) adicionado ao banco %s.", gerente.getNome(), gerente.getCpf(), this.nomeBanco));
        } else {
            System.out.println("Não é possível adicionar gerente nulo ou duplicado.");
        }
    }

    // Método de login
    public Iusuario login(String cpf, String senha) {
        // Tenta encontrar o usuário como Cliente
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf) && c.autenticar(senha)) {
                System.out.println(String.format("Login de Cliente %s (%s) realizado com sucesso.", c.getNome(), c.getCpf()));
                return c;
            }
        }
        // Tenta encontrar o usuário como Gerente
        for (Gerente g : gerentes) {
            if (g.getCpf().equals(cpf) && g.autenticar(senha)) {
                System.out.println(String.format("Login de Gerente %s (%s) realizado com sucesso.", g.getNome(), g.getCpf()));
                return g;
            }
        }
        System.out.println("CPF ou senha incorretos.");
        return null; // Retorna null se o login falhar
    }


    public void listarContas(Gerente gerente) {
        if (gerente != null && gerente.getNivelAcesso() == 2) {
            System.out.println("--- Lista de Contas do " + this.nomeBanco + " ---");
            if (contas.isEmpty()) {
                System.out.println("Nenhuma conta cadastrada.");
            } else {
                for (Conta conta : contas) {
                    conta.imprimirDados();
                }
            }
            System.out.println("------------------------------------");
        } else {
            System.out.println("Acesso negado. Apenas gerentes com nível de acesso 2 podem listar as contas.");
        }
    }

    // Novo método para o Gerente criar contas
    public Conta criarContaParaCliente(Gerente gerente, Cliente cliente, String tipoConta) {
        if (gerente != null && gerente.getNivelAcesso() == 2) {
            if (cliente == null) {
                System.out.println("Não é possível criar conta para um cliente nulo.");
                return null;
            }
            
            Conta novaConta = null;
            if (tipoConta.equalsIgnoreCase("corrente")) {
                novaConta = new contaCorrente(cliente);
                System.out.println(String.format("Gerente %s criou uma Conta Corrente para o cliente %s.", gerente.getNome(), cliente.getNome()));
            } else if (tipoConta.equalsIgnoreCase("poupanca")) {
                novaConta = new contaPoupanca(cliente);
                System.out.println(String.format("Gerente %s criou uma Conta Poupança para o cliente %s.", gerente.getNome(), cliente.getNome()));
            } else {
                System.out.println("Tipo de conta inválido. Escolha 'corrente' ou 'poupanca'.");
                return null;
            }
            this.adicionarConta(novaConta); 
            return novaConta;
        } else {
            System.out.println("Acesso negado. Apenas gerentes com nível de acesso 2 podem criar contas.");
            return null;
        }
    }
}