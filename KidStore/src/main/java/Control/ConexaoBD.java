package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável pela conexão com o banco de dados
 * Princípio SRP (Single Responsibility Principle) - única responsabilidade: gerenciar conexão
 *
 * @author lia
 */
public class ConexaoBD {

    private static final String URL = "jdbc:mysql://localhost:3306/kidstoreBD";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Estabelece conexão com o banco de dados
     * @return Connection objeto de conexão com o banco
     * @throws SQLException se houver erro na conexão
     * @throws ClassNotFoundException se o driver não for encontrado
     */
    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Fecha a conexão com o banco de dados
     * @param conn conexão a ser fechada
     */
    public static void fecharConexao(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}

