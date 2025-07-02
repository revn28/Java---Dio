import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class Atividade {
    public static void main(String[] args) {
        System.out.println("Analisando Candidatos");
        String [] candidatos = {"Felipe", "Neto", "Julia", "Ana", "Roberto"};
        for (String candidato : candidatos){
            entrandoEmContato(candidato);
        }    
        
    
    }

    static void entrandoEmContato(String candidato){
        int tentativaRealizada = 1;
        boolean continuarTentando = true;
        boolean atendeu = false;
        do { 
            atendeu = atender();
            continuarTentando = !atendeu;
            if (continuarTentando){
                tentativaRealizada++;
            }else{
                System.out.println("CONTATO REALIZADO COM SUCESSO");
            }
            
        } while (continuarTentando && tentativaRealizada < 3);

        if (atendeu){
            System.out.println("Conseguimos contato com o candidato " + candidato + " na " + tentativaRealizada  +" tentativa");
        }else{
            System.out.println("Não conseguimos contato com o " + candidato + " Numero maximo de tentativa " + tentativaRealizada + " Realizada"  );
        }
    }

    // metodo auxiliar
    static boolean atender(){
        return new Random().nextInt(3 ) ==1;
    }

    static void imprimindoSelecionados(){
        String [] candidatos = {"Felipe", "Neto", "Julia", "Ana", "Roberto"};
        // for( int indice = 0; indice < candidatos.length; indice++ ){
        //     System.out.println("O candidato " + (indice+1) + " é o " + candidatos[indice] + " selecionado para a vaga");
        // }

        System.out.println("Usando For each ");
        for(String candidato : candidatos){
            System.out.println("O candidato " + candidato + " selecionado");
        }
    }

    static void selecaoCandidato(){
        String [] candidatos = {"Felipe", "Neto", "Julia", "Ana", "Roberto", "Gabriel", "Natan"};
        int candidatoSelecionado = 0;
        int candidatoAtual = 0;
        double salarioBase = 2000.0;
        
        while(candidatoSelecionado < 5 && candidatoAtual < candidatos.length){
            String candidato = candidatos[candidatoAtual];
            double salarioPretendido = valorPretendido();
            System.out.println( "O candidato "+ candidato + " solicitou o salario de: " + salarioPretendido );
            
            if (salarioBase >= salarioPretendido ){
                System.out.println("O candidato " + candidato + " foi selecionado para a vaga!");
                candidatoSelecionado++;
            }else{
                System.out.println("Analisando outro candidato");
            }
            candidatoAtual++;
        }

    }

    static double valorPretendido(){
        return ThreadLocalRandom.current().nextDouble(1800,2200);
    }
    
    static void analisandoCandidatos(double valorPretendido){
            double salarioBase = 2000.0;
            if(salarioBase > valorPretendido){
                System.out.println("ligar para o candidato");
                
            }else if(salarioBase == valorPretendido){
                System.out.println("Ligar para o candidato com contra porposta");
            }else{
                System.out.println("Aguardando resultado demais candidatos");
            }

    }
}
