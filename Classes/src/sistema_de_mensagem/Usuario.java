package sistema_de_mensagem;

import java.util.Scanner;
import sistema_de_mensagem.BaseApp.FacebookMessage;
import sistema_de_mensagem.BaseApp.MSNMessenger;
import sistema_de_mensagem.BaseApp.Telegram;

public class Usuario {
    public static void main(String[] args) {
        System.out.println("Bem vindo Usuario");
        Scanner entrada = new Scanner(System.in);
        System.out.println("Selecione o Aplicativo: 'msn', 'facebook', 'telegram'" );
        // entrada.next();
        String app = entrada.next();

        if (app.equals("msn")){
            MSNMessenger msn = new MSNMessenger();
                System.out.println("Enviar mensagem: ");
                String textoMsn = entrada.next();
            msn.enviarMensagem(textoMsn);
            entrada.close();
        }else if (app.equals("facebook")){
            FacebookMessage fbc = new FacebookMessage();
                System.out.println("Enviar mensagem: ");
                    String textoFabc = entrada.next();
            fbc.enviarMensagem(textoFabc);
            entrada.close();
        }else if (app.equals("telegram")){
            Telegram tlg = new Telegram();
                System.out.println("Enviar mensagem: ");
                    String textoTlg = entrada.next();
            tlg.enviarMensagem(textoTlg);
            entrada.close();
        }else{
            System.out.println("appp invalido");
        }


    }
}
