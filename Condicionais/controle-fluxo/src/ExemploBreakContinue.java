public class ExemploBreakContinue {

    public static void main(String[] args) {
        for (int numero = 0; numero <= 5; numero++) {
            if(numero == 3)
               // break; // imprime ate o numero 2
                continue; // imprime 1,2 pula o 3 e continua ate 5 
            
            System.out.println(numero);
        }
    }
}
