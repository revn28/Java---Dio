package sistema_de_mensagem.BaseApp;

public abstract  class SistemaBase {
    public abstract void enviarMensagem(String texto);
        // validarConexaoInternet();
        // System.out.println("Mensagem enviada");
        // salvarHistoricoMensagem();
    
    public abstract  void receberMensagem();
        // validarConexaoInternet();
        // System.out.println("Recebendo mensagem");
        // salvarHistoricoMensagem();
    protected void validarConexaoInternet(){
        System.out.println("Conectado a internet"); 
    }
    // private void salvarHistoricoMensagem(){
    //     System.out.println("Salvado historico de mensagens");
    // }


    // public int calculoBo(int a, int b){
    //     int soma = a + b;
    //     return soma;
        
    // }

}
