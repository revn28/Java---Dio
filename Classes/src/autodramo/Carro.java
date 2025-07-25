package autodramo;

public class Carro extends Veiculo {
    public void ligarCarro(){
        verificarCombustivel();
        verificarCambio();
        System.out.println("Carro Ligado");
    }
    private void verificarCombustivel(){
        System.out.println("Conbustivel verificado");
    }
    private void verificarCambio(){
        System.out.println("Cambio em P");
    }
}
