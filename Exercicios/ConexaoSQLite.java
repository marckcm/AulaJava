
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLite {

    public static Connection conectar() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:produtos.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
        return conn;
    }
}
