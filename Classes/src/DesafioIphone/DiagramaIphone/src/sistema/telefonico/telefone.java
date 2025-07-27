package sistema.telefonico;

public class telefone implements sistemaTelefonico   {
    @Override
    public void ligar(String numero) {
        System.out.println("Ligar para: " + numero);
    }

    @Override
    public void atender() {
        System.out.println("Atender");
    }

    // @Override
    // public void desligar() {
    //     System.out.println("Desligar");
    // }

    @Override
    public void iniciarCorreioDeVoz() {
        System.out.println("- Iniciar Correio de Voz");
    }
}
