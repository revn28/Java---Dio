package sistema_de_mensagem.BaseApp;

public class FacebookMessage extends SistemaBase {

    @Override
    public void enviarMensagem(String texto) {
        System.out.println( texto + " (Mensagem enviada pelo Facebook)");
    }

    @Override
    public void receberMensagem() {
        System.out.println("Mensagem recebida pelo Facebook");
    }
    
}
