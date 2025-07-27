package sistema.telefonico;

public interface sistemaTelefonico {
    void ligar(String modelo);
    void atender();
    void desligar();
    void iniciarCorreioDeVoz();
    // void enviarMensagem(String numero, String mensagem);
    // void receberMensagem(String mensagem);
}
