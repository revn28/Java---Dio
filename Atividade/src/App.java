
import java.util.Scanner;

// 1. Escreva um programa que, com base em uma temperatura em graus celsius, 
// a converta e exiba em Kelvin (K), Réaumur (Re), Rankine (Ra) e Fahrenheit (F), 
// seguindo as fórmulas: F = C * 1.8 + 32; K = C + 273.15; Re = C * 0.8; Ra = C * 1.8 + 32 + 459.67
public class App {
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);            
        System.out.println("Hello, World!");
        float c = 0;
        float f = 0;
        float re = 0;
        float ra = 0;
        
        System.out.println("Digite (K)Kelvin (Re)Réaumur (Ra)Rankine (F)Fahrenheit");
        String usuario = entrada.next();
        
        switch (usuario) {
            case "k" :
            float k = 0;
                    
                break;
            default:
                throw new AssertionError();
        }
        c = entrada.nextInt();

    }

    public void Kelvin(float c){
        double k;
        k = c + 273.15;
        
    }

}

