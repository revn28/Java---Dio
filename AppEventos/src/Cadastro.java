// Conteúdo de Cadastro.java - Adicionar o novo construtor
public class Cadastro {
    // Adicionado um atributo 'id' para armazenar o ID do banco de dados
    private int id; // Tornei private para seguir as boas práticas de encapsulamento
    String nomeCompleto = "";
    String apelido = "";
    String email = "";
    String dataNascimento = "";
    String telefone = "";
    String senhaCadastro = "";
    String cpf = "";
    

    // Construtor original (sem ID), para quando o usuário está sendo cadastrado pela primeira vez
    public Cadastro(String email, String senhaCadastro, String nomeCompleto, String apelido, String dataNascimento, String cpf, String telefone ) {
        this.email = email;
        this.senhaCadastro = senhaCadastro;
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        
    }

    // NOVO CONSTRUTOR: Para quando recuperamos o usuário do banco de dados (que já tem um ID)
    public Cadastro(int id, String email, String senhaCadastro, String nomeCompleto, String apelido, String dataNascimento, String cpf, String telefone ) {
        this.id = id; // Inicializa o ID
        this.email = email;
        this.senhaCadastro = senhaCadastro;
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
       
    }


    // Getters para acessar os dados (boa prática de encapsulamento)
    // Adicionado o getter para o ID
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenhaCadastro() {
        return senhaCadastro;
    }
    // Opcional: Você pode adicionar um setter para o ID, mas geralmente o ID é gerado pelo banco e não modificado.
    public void setId(int id) {
        this.id = id;
    }
    public String getApelido(){
        return apelido;
    }
    // ... outros getters se você precisar

    // Para melhor encapsulamento, é uma boa prática tornar os atributos private
    // e usar apenas getters/setters. Exemplo:
    // private String nomeCompleto;
    // public String getNomeCompleto() { return nomeCompleto; }
    // public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    // Por enquanto, vou manter como está para não mudar muito o código existente,
    // mas é algo a se considerar para refatorações futuras.
}