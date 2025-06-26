// Conteúdo de EventoDAO.java (Atualizado)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime; // Importação necessária
import java.time.format.DateTimeFormatter; // Importação necessária
import java.util.ArrayList;
import java.util.Collections; // Importação necessária para Collections.sort
import java.util.Comparator; // Importação necessária para Comparator
import java.util.List;

public class EventoDAO {

    private static final String URL = "jdbc:sqlite:usuarios.db";
    // Formato padrão para armazenar LocalDateTime como String no SQLite
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void criarTabela() {
        // Inclui 'horarioFim' na criação da tabela
        String sql = "CREATE TABLE IF NOT EXISTS eventos (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "usuarioId INTEGER NOT NULL," +
                     "nome TEXT NOT NULL," +
                     "endereco TEXT," +
                     "categoria TEXT," +
                     "horarioInicio TEXT," + // Armazenado como TEXT
                     "horarioFim TEXT," +    // Novo campo, armazenado como TEXT
                     "descricao TEXT," +
                     "FOREIGN KEY (usuarioId) REFERENCES usuarios(id) ON DELETE CASCADE" +
                     ");";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Tabela 'eventos' criada ou já existe.");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela 'eventos': " + e.getMessage());
        }
    }

    public boolean inserirEvento(Evento evento) {
        // Adapta o SQL para incluir horarioFim
        String sql = "INSERT INTO eventos(usuarioId, nome, endereco, categoria, horarioInicio, horarioFim, descricao) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evento.getUsuarioId());
            stmt.setString(2, evento.getNome());
            stmt.setString(3, evento.getEndereco());
            stmt.setString(4, evento.getCategoria());
            // Converte LocalDateTime para String no formato ISO para armazenamento
            stmt.setString(5, evento.getHorarioInicio().format(FORMATTER));
            stmt.setString(6, evento.getHorarioFim().format(FORMATTER)); // Salva horarioFim
            stmt.setString(7, evento.getDescricao());
            stmt.executeUpdate();
            System.out.println("Evento '" + evento.getNome() + "' inserido com sucesso.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir evento: " + e.getMessage());
            return false;
        }
    }

    // Método auxiliar para construir um objeto Evento a partir de um ResultSet
    private Evento criarEventoDoResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int usuarioId = rs.getInt("usuarioId");
        String nome = rs.getString("nome");
        String endereco = rs.getString("endereco");
        String categoria = rs.getString("categoria");
        // Converte String do banco de dados para LocalDateTime
        LocalDateTime horarioInicio = LocalDateTime.parse(rs.getString("horarioInicio"), FORMATTER);
        LocalDateTime horarioFim = LocalDateTime.parse(rs.getString("horarioFim"), FORMATTER); // Recupera horarioFim
        String descricao = rs.getString("descricao");

        return new Evento(id, usuarioId, nome, endereco, categoria, horarioInicio, horarioFim, descricao);
    }

    public List<Evento> listarEventosPorUsuario(int usuarioId) {
        List<Evento> eventos = new ArrayList<>();
        // Adapta o SQL para selecionar horarioFim
        String sql = "SELECT id, usuarioId, nome, endereco, categoria, horarioInicio, horarioFim, descricao FROM eventos WHERE usuarioId = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    eventos.add(criarEventoDoResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar eventos do usuário: " + e.getMessage());
        }
        // Ordena os eventos pelo horário de início
        Collections.sort(eventos, Comparator.comparing(Evento::getHorarioInicio));
        return eventos;
    }

    public List<Evento> listarTodosEventos() {
        List<Evento> eventos = new ArrayList<>();
        // Adapta o SQL para selecionar horarioFim
        String sql = "SELECT id, usuarioId, nome, endereco, categoria, horarioInicio, horarioFim, descricao FROM eventos";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    eventos.add(criarEventoDoResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todos os eventos: " + e.getMessage());
        }
        // Ordena os eventos pelo horário de início
        Collections.sort(eventos, Comparator.comparing(Evento::getHorarioInicio));
        return eventos;
    }

    public Evento buscarEventoPorId(int eventoId) {
        // Adapta o SQL para selecionar horarioFim
        String sql = "SELECT id, usuarioId, nome, endereco, categoria, horarioInicio, horarioFim, descricao FROM eventos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarEventoDoResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar evento por ID: " + e.getMessage());
        }
        return null;
    }

    public boolean excluirEvento(int eventoId) {
        String sql = "DELETE FROM eventos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventoId);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Evento com ID " + eventoId + " excluído com sucesso.");
                return true;
            } else {
                System.out.println("Nenhum evento encontrado com ID " + eventoId + " para exclusão.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir evento: " + e.getMessage());
            return false;
        }
    }
}