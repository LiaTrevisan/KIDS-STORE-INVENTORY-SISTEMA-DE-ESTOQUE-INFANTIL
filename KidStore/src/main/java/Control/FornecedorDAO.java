/*
 * DAO responsável pelas operações de CRUD de Fornecedor
 * Princípios SOLID aplicados:
 * - SRP: Responsável apenas por operações de banco de dados de Fornecedor
 * - OCP: Aberto para extensão através de novos métodos
 */
package Control;
import Model.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para fornecedores de produtos infantis
 *
 * @author lia
 */

public class FornecedorDAO {

    // Método para inserir fornecedor
    public void inserirFornecedor(Fornecedor fornecedor) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "INSERT INTO Fornecedor (nome) VALUES (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, fornecedor.getNome());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao inserir fornecedor: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para atualizar fornecedor
    public void atualizarFornecedor(Fornecedor fornecedor) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "UPDATE Fornecedor SET nome = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, fornecedor.getNome());
            ps.setInt(2, fornecedor.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao atualizar fornecedor: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para deletar fornecedor
    public void deletarFornecedor(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "DELETE FROM Fornecedor WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao deletar fornecedor: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para buscar fornecedor por ID
    public Fornecedor buscarFornecedorPorId(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Fornecedor fornecedor = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Fornecedor WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar fornecedor: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return fornecedor;
    }

    // Método para listar todos os fornecedores
    public List<Fornecedor> listarTodosFornecedores() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Fornecedor";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedores.add(fornecedor);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao listar fornecedores: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return fornecedores;
    }
}