
public class Operadores {

    public static void main(String[] args) {

        // int numero1 = 5;
        // int numero2 = 3;
        // int soma = numero1 + numero2;
        // int subtracao = numero1 - numero2;
        // int multiplicacao = numero1 * numero2;
        // int divisao = numero1 / numero2;
       
        // int numero = 2;
        // numero = - numero;
        // numero = numero * -1;
        
        // System.out.println(numero);
        // System.out.println(divisao);
         
        //incremento ou decremento

        // int numero3 = 1;
        //     numero3 ++;
        // System.err.println(numero3); // resultado = 2
        // -----------------------------------------------------------

        // valores boleanos

        // boolean variavel = true;
        // System.out.println(!variavel); // simbolo de "!" inverte o valor
        // System.out.println(variavel);
        // --------------------------------------------------------------------------
    
        // operadores Ternario
        
        // int a,b;
        // a = 5;
        // b = 6;
        // String resultado = "";
        // if (a==b)
        //     resultado = "verdadiro";
        // else
        //     resultado = "falso";
        
        // String resultado = a == b ? "verdadeiro" : "falso";
        // System.out.println(resultado);
        //----------------------------------------------------------------------------------------------
        // operadores de igualdade "== " e "!="
        // String nomeUm = "Roberto";
        // String nomeDois = new String ("Roberto");
        // comparar um valor da variavel com o valor dentro de um novo objeto
        // System.out.println(nomeUm.equals(nomeDois)); // usamos "equals" para comparar os valores dentro dos objetos
        
        // boolean resultado = nomeUm != nomeDois;
        // System.out.println(resultado);
        // ----------------------------------------------------------------------------------------------------
        // operadores && e ||
        boolean condicao1 = true;
        boolean condicao2 = true;

        String resultado = condicao1 && condicao2 ? "As duas condições são verdadeiras" :  "Uma das condiçoes é falsa";
        
        String resultado2 = condicao1 || condicao2 ? "Uma das condições é verdadeira" : "as duas condições sao falsas";

        System.out.println(resultado);
        System.out.println(resultado2);


    }
}
