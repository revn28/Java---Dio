package sistema.navegador;

public class navegadorInternet implements sistemaNavegadorInternet {
    @Override
    public void abrirPagina(String url) {
        System.out.println("Abrir Página: "  + url);
    }

    @Override
    public void atualizarPagina() {
        System.out.println("Atualizar Página");
    }

    @Override
    public void abrirNovaAba() {
        System.out.println("Abrir Nova Aba");
    }

   
}
