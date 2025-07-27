package sistema.multimidia;

public class reproducaoMusica implements sistemaReprodutorMusical {
    @Override
    public void tocarMusica() {
        System.out.println("Tocar Música");
    }

    @Override
    public void pausarMusica() {
        System.out.println("Pausar Música");
    }

    @Override
    public void selecionarMusica(String musica) {
        System.out.println("Selecionar Música: " + musica);
    }

   

}
