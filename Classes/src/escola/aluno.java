package escola;
public class aluno{
    public static void main(String[] args) {
        aluno Roberto = new aluno(9,8);
        Roberto.nome = "Neto";
        Roberto.idade = 29;
        

        System.out.println("Aluno: " + Roberto.getNome()+ " tenho: " + Roberto.getIdade() +" anos.");
        
        
        
    }
    private String nome;
    private int idade;
    private int sala;
    private int turma;

    public String getNome(){
        return nome;
    }   
    
    public void setNome(String newNome){
        nome = newNome;

    }

    public int getIdade(){
        return idade;

    }
    public void setIdade(int idade){
        this.idade = idade;
    }

    public aluno(int sala, int turma){
        this.sala = sala;
        this.turma = turma;
    }


}