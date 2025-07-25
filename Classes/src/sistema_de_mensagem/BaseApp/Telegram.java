package sistema_de_mensagem.BaseApp;

public class Telegram extends SistemaBase{

    @Override
    public void enviarMensagem(String texto) {
        System.out.println(texto + " MMensagem enviada pelo TELEGRAM");
    }

    @Override
    public void receberMensagem() {
     System.out.println("Mensagem recebida pelo TELEGRAM");
    }
    
}
