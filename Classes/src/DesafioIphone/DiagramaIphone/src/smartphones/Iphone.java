package smartphones;
public class Iphone {
    public static void main(String[] args) throws Exception {
        
        // Criando instâncias dos sistemas
        sistema.multimidia.reproducaoMusica musica = new sistema.multimidia.reproducaoMusica();
        sistema.navegador.navegadorInternet navegador = new sistema.navegador.navegadorInternet();
        sistema.telefonico.telefone telefone = new sistema.telefonico.telefone();   
        
        // Usando os métodos dos sistemas
        musica.tocarMusica();   
        musica.pausarMusica();
        musica.selecionarMusica("Minha Música Favorita");

        navegador.abrirPagina( "https://www.exemplo.com");
        navegador.abrirNovaAba();
        navegador.atualizarPagina();
        
        telefone.ligar("123-456-7890");
        telefone.atender();
        telefone.iniciarCorreioDeVoz();

    }
}
