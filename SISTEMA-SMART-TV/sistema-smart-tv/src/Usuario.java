/**
  @author Roberto Vieira
 * @version 1.0
 * @since 03/06/2025 
 * 
 */


public class Usuario {
    public static void main(String[] args) throws Exception {
        SmartTv smartTv = new SmartTv();

        System.out.println("A tv esta ligada? "+ smartTv.ligada);
        System.out.println("Qual o volume da tv: "+ smartTv.volume);
        System.out.println("Qual o canal: "+ smartTv.canal);

        smartTv.ligar();
        System.out.println("A tv esta ligada? "+ smartTv.ligada);
        smartTv.aumentarVolume();
        System.out.println("aumentar volume: "+ smartTv.volume);
        
        smartTv.mudarCanal(8);
        System.out.println("Qual o canal: "+ smartTv.canal);
        
    }
}
