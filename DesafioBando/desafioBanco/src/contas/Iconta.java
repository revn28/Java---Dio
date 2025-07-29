package contas;
public interface Iconta {
    void sacar(double  valor);  
    void depositar(double  valor);
    void transferir(double  valor, Conta contaDestino);
    void imprimirDados();
    void investir(double valor, contaPoupanca contaPoupanca);
    

}
