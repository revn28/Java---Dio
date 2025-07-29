package contas;
import usuario.Cliente;

public abstract class Conta implements Iconta {
    private  static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    
    protected double saldo; 
    protected int conta;
    protected int agencia;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.conta = SEQUENCIAL++;
        this.cliente = cliente;
        this.saldo = 0.0; 
    }
  
    public double getSaldo() { 
        return saldo;
    }

    public int getconta() {
        return conta;   
    }
    public int getagencia() {
        return agencia;
    }


    @Override
    public void sacar(double valor) {
        if (valor > 0 && this.saldo >= valor) { // Adicionando verificação para valor positivo e suficiente
            saldo -= valor;
        } else {
            System.out.println("Não foi possível realizar o saque. Verifique o valor ou o saldo.");
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) { // Adicionando verificação para valor positivo
            saldo += valor;
        } else {
            System.out.println("Não foi possível realizar o depósito. O valor deve ser positivo.");
        }
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (valor > 0 && this.saldo >= valor) { // Verifica se há saldo e valor é positivo
            this.sacar(valor);
            contaDestino.depositar(valor);
            System.out.println(String.format("Transferência de R$%.2f realizada de conta %d para conta %d.", valor, this.conta, contaDestino.getconta()));
        } else {
            System.out.println(String.format("Não foi possível realizar a transferência de R$%.2f. Saldo insuficiente ou valor inválido.", valor));
        }
    }

    
    @Override
    public void imprimirDados(){
        // Formata o saldo para exibir duas casas decimais
        System.out.println(String.format("Titular: %s | Agência: %d | Conta: %d | Saldo: R$%.2f", this.cliente.nome ,this.agencia, this.conta, this.saldo));
    }

    @Override
    public void investir(double valor, contaPoupanca contaPoupanca) {
        if (valor <= 0) {
            System.out.println("Valor para investir deve ser positivo.");
            return;
        }

        if (this.saldo >= valor) {
            this.sacar(valor);
            contaPoupanca.depositar(valor);
            System.out.println(String.format("Investimento de R$%.2f realizado da conta %d para a conta poupança %d.", valor, this.conta, contaPoupanca.getconta()));
        } else {
            System.out.println(String.format("Saldo insuficiente para investir R$%.2f. Saldo atual: R$%.2f", valor, this.saldo));
        }
    }
}