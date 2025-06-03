package edu.roberto.primeirasemana;

public class MinhaClasse {
    
    // public static void main(String[] args) {
    //     System.out.println("Ola Roberto");

    //     System.out.println("gato de botas");
        

    // }
    public static void main(String[] args) {
        // numeclaturas de variaveis
        String apelido = "Neto";
        String nome = "Roberto";
        String sobrenome = "Vieira";
        String dataNascimento = "10/09/1995";
        int idade = 29;

        System.out.println("Apelido: " +  apelido);
        System.out.println("Data de nascimento: " + dataNascimento);
        System.out.println("Idade: " + idade + " Anos");

        String nomeCompleto = nomeCompleto(nome, sobrenome);
        System.out.println(nomeCompleto);
    }

    public static String nomeCompleto (String primeiroNome, String segundoNome) {
        return  (primeiroNome + " " + segundoNome);
    }

}

