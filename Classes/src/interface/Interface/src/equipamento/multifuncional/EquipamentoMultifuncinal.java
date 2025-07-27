package equipamento.multifuncional;

import equipamento.copiadora.Copiadora;
import equipamento.digitalizadora.Digitalizadora;
import equipamento.impressora.Impressora;

public class EquipamentoMultifuncinal implements Impressora, Copiadora, Digitalizadora{

    @Override
    public void imprimir() {
        System.out.println("IMPRIMINDO PELA Multifncional");
    }

    @Override
    public void digitalizar() {
        System.out.println("DIGITALIZANDO PELA Multifncional");
    }

    @Override
    public void copiar() {
        System.out.println("COPIANDO PELA Multifncional");
    }
    
}
