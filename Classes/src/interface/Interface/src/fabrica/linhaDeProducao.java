package fabrica;

import equipamento.copiadora.Copiadora;
import equipamento.copiadora.Xerox;
import equipamento.digitalizadora.Digitalizadora;
import equipamento.impressora.Deskjet;
import equipamento.impressora.Impressora;
import equipamento.multifuncional.EquipamentoMultifuncinal;
import java.util.Scanner;

public class linhaDeProducao {
    public static void main(String[] args) {
        EquipamentoMultifuncinal eq = new EquipamentoMultifuncinal();

            eq.imprimir();
            eq.digitalizar();
            eq.copiar();
       
        Xerox xerox = new Xerox();
            
            xerox.copiar();

        equipamento.digitalizadora.Scanner sc = new equipamento.digitalizadora.Scanner();
            
            sc.digitalizar();

        Impressora desk = new Deskjet();

            desk.imprimir();

    }
}
