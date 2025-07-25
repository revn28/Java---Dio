package escola;

public enum EstadoBrasileiro {
    SAO_PAULO ("SP","Sao Paulo", 11),
    RIO_DE_JANEIRO("RJ", "Rio de Janeiro", 12),
    BAHIA("BA", "Bahia", 71) ;


    private String nome;
    private String sigla;
    private int ibge;

    private EstadoBrasileiro(String sigla, String nome, int ibge){
        this.sigla = sigla;
        this.nome = nome;
        this.ibge = ibge;
    }

    public int getIbge(){
        return  ibge;
    }

    public String getSigla(){
        return sigla;
    }

    public String getNome(){
        return nome;
    }

    public String getNomeMaiusculo(){
        return nome.toUpperCase();
    }

}

