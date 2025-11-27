/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para funcionários
 * Princípio SRP: Responsável apenas por operações de BD de Funcionario
 *
 * @author lia
 */
public class FuncionarioDAO {

    // Método para inserir funcionário
    public void inserirFuncionario(Funcionario funcionario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "INSERT INTO Funcionario (usuario_id) VALUES (?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, funcionario.getIdFunc());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para atualizar funcionário
    public void atualizarFuncionario(Funcionario funcionario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "UPDATE Funcionario SET usuario_id = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, funcionario.getIdFunc());
            ps.setInt(2, funcionario.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para deletar funcionário
    public void deletarFuncionario(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "DELETE FROM Funcionario WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao deletar funcionário: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para buscar funcionário por ID
    public Funcionario buscarFuncionarioPorId(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Funcionario funcionario = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Funcionario WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("cargo"),
                    rs.getInt("usuario_id")
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return funcionario;
    }

    // Método para listar todos os funcionários
    public List<Funcionario> listarTodosFuncionarios() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Funcionario";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("cargo"),
                    rs.getInt("usuario_id")
                );
                funcionarios.add(funcionario);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return funcionarios;
    }
}