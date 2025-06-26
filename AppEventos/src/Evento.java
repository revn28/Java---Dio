// Conteúdo de Evento.java (Atualizado)
import java.time.LocalDateTime; // Importação necessária para LocalDateTime

public class Evento {
    private int id;
    private int usuarioId; // ID do usuário que criou o evento
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horarioInicio; // Alterado para LocalDateTime
    private LocalDateTime horarioFim;    // Novo campo para horário de término
    private String descricao;

    // Construtor para quando o evento é criado (ID ainda não definido pelo BD)
    public Evento(int usuarioId, String nome, String endereco, String categoria, LocalDateTime horarioInicio, LocalDateTime horarioFim, String descricao) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.descricao = descricao;
    }

    // Construtor para quando o evento é recuperado do banco de dados (ID já definido)
    public Evento(int id, int usuarioId, String nome, String endereco, String categoria, LocalDateTime horarioInicio, LocalDateTime horarioFim, String descricao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.descricao = descricao;
    }

    // --- Getters ---
    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public LocalDateTime getHorarioFim() { // Getter para o horário de término
        return horarioFim;
    }

    public String getDescricao() {
        return descricao;
    }

    // --- Setters (se necessário, adicione para atributos que podem ser alterados) ---
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // ... outros setters conforme sua necessidade
}