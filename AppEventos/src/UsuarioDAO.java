// Conteúdo de UsuarioDAO.java - SÓ O MÉTODO inserirUsuario PRECISA SER ATUALIZADO
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// ... (outros imports e código da classe UsuarioDAO)

public class UsuarioDAO {
    private static final String URL = "jdbc:sqlite:usuarios.db";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "email TEXT NOT NULL UNIQUE," +
                     "senhaCadastro TEXT NOT NULL," +
                     "nomeCompleto TEXT," +
                     "apelido TEXT," +
                     "dataNascimento TEXT," +
                     "telefone TEXT," +
                     "cpf TEXT )" 
                    ;
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Tabela 'usuarios' criada ou já existe.");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    /**
     * Insere um novo usuário no banco de dados e retorna o ID gerado.
     * @param usuario O objeto Cadastro contendo os dados do usuário a ser inserido.
     * @return O ID do usuário inserido, ou -1 se a inserção falhou.
     */
    public int inserirUsuario(Cadastro usuario) { // Retorna int agora
        String sql = "INSERT INTO usuarios(email, senhaCadastro, nomeCompleto, apelido, dataNascimento, telefone, cpf) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int idGerado = -1; // Valor padrão para falha
        try (Connection conn = conectar();
             // Adicionamos Statement.RETURN_GENERATED_KEYS para obter o ID gerado
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenhaCadastro());
            stmt.setString(3, usuario.nomeCompleto);
            stmt.setString(4, usuario.apelido);
            stmt.setString(5, usuario.dataNascimento);
            stmt.setString(6, usuario.telefone);
            stmt.setString(7, usuario.cpf);
            int affectedRows = stmt.executeUpdate(); // Retorna o número de linhas afetadas

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) { // Obtém as chaves geradas
                    if (rs.next()) {
                        idGerado = rs.getInt(1); // O primeiro ID gerado
                        System.out.println("Usuário " + usuario.getEmail() + " inserido com sucesso (ID: " + idGerado + ").");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
        return idGerado;
    }

    public Cadastro buscarUsuarioParaLogin(String email, String senha) {
        String sql = "SELECT id, email, senhaCadastro, nomeCompleto, apelido, dataNascimento, cpf, telefone FROM usuarios WHERE email = ? AND senhaCadastro = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Crie o objeto Cadastro, passando o ID recuperado do banco
                    return new Cadastro(
                        rs.getInt("id"), // Adicionado para recuperar o ID
                        rs.getString("email"),
                        rs.getString("senhaCadastro"),
                        rs.getString("nomeCompleto"),
                        rs.getString("apelido"),
                        rs.getString("dataNascimento"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário para login: " + e.getMessage());
        }
        return null;
    }

    public boolean verificarEmailExistente(String email) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar email existente: " + e.getMessage());
        }
        return false;
    }
}