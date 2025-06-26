// Conteúdo de ParticipacaoEventoDAO.java (Atualizado)
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

public class ParticipacaoEventoDAO {

    private static final String URL = "jdbc:sqlite:usuarios.db";
    // Formato padrão para lidar com LocalDateTime como String no SQLite
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS participacoes_evento (" +
                     "usuarioId INTEGER NOT NULL," +
                     "eventoId INTEGER NOT NULL," +
                     "status TEXT NOT NULL," + // Ex: 'confirmado', 'cancelado'
                     "PRIMARY KEY (usuarioId, eventoId)," + // Chave primária composta
                     "FOREIGN KEY (usuarioId) REFERENCES usuarios(id) ON DELETE CASCADE," +
                     "FOREIGN KEY (eventoId) REFERENCES eventos(id) ON DELETE CASCADE" +
                     ");";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Tabela 'participacoes_evento' criada ou já existe.");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela 'participacoes_evento': " + e.getMessage());
        }
    }

    public boolean confirmarPresenca(int usuarioId, int eventoId) {
        // Primeiro, verifica se já existe uma participação para este usuário e evento
        String checkSql = "SELECT COUNT(*) FROM participacoes_evento WHERE usuarioId = ? AND eventoId = ?";
        // Se existir, atualiza. Senão, insere.
        String upsertSql = "INSERT INTO participacoes_evento (usuarioId, eventoId, status) VALUES (?, ?, 'confirmado') " +
                           "ON CONFLICT(usuarioId, eventoId) DO UPDATE SET status = 'confirmado'";
        try (Connection conn = conectar();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement upsertStmt = conn.prepareStatement(upsertSql)) {

            // Executa o UPSERT (INSERT OR UPDATE)
            upsertStmt.setInt(1, usuarioId);
            upsertStmt.setInt(2, eventoId);
            upsertStmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao confirmar presença: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelarPresenca(int usuarioId, int eventoId) {
        String sql = "UPDATE participacoes_evento SET status = 'cancelado' WHERE usuarioId = ? AND eventoId = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, eventoId);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao cancelar presença: " + e.getMessage());
            return false;
        }
    }

    public String verificarStatusPresenca(int usuarioId, int eventoId) {
        String sql = "SELECT status FROM participacoes_evento WHERE usuarioId = ? AND eventoId = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, eventoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar status de presença: " + e.getMessage());
        }
        return null; // Não encontrado
    }

    public List<Evento> listarEventosConfirmadosPorUsuario(int usuarioId) {
        List<Evento> eventosConfirmados = new ArrayList<>();
        // Faz um JOIN com a tabela de eventos para obter os detalhes completos do evento
        // Inclui e.usuarioId (criador do evento), e.horarioInicio e e.horarioFim
        String sql = "SELECT e.id, e.usuarioId, e.nome, e.endereco, e.categoria, e.horarioInicio, e.horarioFim, e.descricao " +
                     "FROM eventos e " +
                     "JOIN participacoes_evento pe ON e.id = pe.eventoId " +
                     "WHERE pe.usuarioId = ? AND pe.status = 'confirmado'";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Recupera os horários como String e converte para LocalDateTime
                    LocalDateTime horarioInicio = LocalDateTime.parse(rs.getString("horarioInicio"), FORMATTER);
                    LocalDateTime horarioFim = LocalDateTime.parse(rs.getString("horarioFim"), FORMATTER);
                    
                    Evento evento = new Evento(
                        rs.getInt("id"),
                        rs.getInt("usuarioId"), // Corrigido: Agora pega o ID do CRIADOR do evento da tabela 'eventos'
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("categoria"),
                        horarioInicio, // Passa o LocalDateTime
                        horarioFim,    // Passa o LocalDateTime
                        rs.getString("descricao")
                    );
                    eventosConfirmados.add(evento);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar eventos confirmados: " + e.getMessage());
        }
        // Ordena os eventos pelo horário de início para que os mais próximos apareçam primeiro
        Collections.sort(eventosConfirmados, Comparator.comparing(Evento::getHorarioInicio));
        return eventosConfirmados;
    }
}
