package usuario;
public class Cliente implements Iusuario {
    public String nome;
    public String cpf;
    public String email;
    public String dataNascimento;
    public String telefone;
    public int nivelAcesso = 1; // Nível de acesso padrão
    private int id; 
    private String senha;

    // nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // cpf
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    // email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    // telefone
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // data de nascimento
    public String getDataNascimento() { 
        return dataNascimento;
    }
    public void setdataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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
    public int getId() {
        return this.id;
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