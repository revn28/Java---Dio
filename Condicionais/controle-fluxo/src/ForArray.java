public class ForArray {
    public static void main(String[] args) {
        // em arrays o indice inicia com 0
        String alunos [] = {"FELIPE", "jULIA", "MARCOS"};
        // for (int x = 0; x < alunos.length; x++){

        //     System.out.println("O aluno no indice x = " + x + " é " + alunos [x] );
        // }

        for (Object aluno : alunos) {
            System.out.println("O aluno é: " + aluno);
            
        }
    }
}
