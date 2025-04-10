import java.sql.Connection;
import java.sql.Statement;

public class CriarBanco {
    public static void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nome TEXT NOT NULL," +
                     "preco REAL NOT NULL);";
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        criarTabela();
    }
}