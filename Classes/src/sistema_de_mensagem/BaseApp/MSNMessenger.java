package sistema_de_mensagem.BaseApp;

public class MSNMessenger extends SistemaBase {

    @Override
    public void enviarMensagem(String texto) {
        validarConexaoInternet();
        System.out.println(texto + " (Mensagem enviada pelo MSN)");
    }

    @Override
    public void receberMensagem() {
        System.out.println("Mensagem recebida pelo MSN");
    }

    
}
