package usuario;

public class Gerente implements Iusuario {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String dataNascimento;
    private String telefone;
    private int nivelAcesso;
    private String senha; 

    public Gerente(int id, String nome, String cpf, String email, String dataNascimento, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.nivelAcesso = 2; // Nível de acesso para gerente
        this.senha = senha; 
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    // Senha
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    
    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public int getNivelAcesso() {
        return this.nivelAcesso;
    }
}