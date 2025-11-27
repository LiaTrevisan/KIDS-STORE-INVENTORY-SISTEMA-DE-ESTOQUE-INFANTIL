/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para usuários do sistema
 * Princípio SRP: Responsável apenas por operações de BD de Usuario
 *
 * @author lia
 */
public class UsuarioDAO {

    // Método para inserir usuário
    public void inserirUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "INSERT INTO Usuario (nome, email, senha, cargo) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getCargo());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para atualizar usuário
    public void atualizarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, cargo = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getCargo());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para deletar usuário
    public void deletarUsuario(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "DELETE FROM Usuario WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para buscar usuário por ID
    public Usuario buscarUsuarioPorId(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Usuario WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cargo")
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return usuario;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Usuario WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cargo")
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return usuario;
    }

    public Usuario buscarUsuarioPorSenha(String senha) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Usuario WHERE senha = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, senha);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cargo")
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return usuario;
    }

// Método para listar todos os usuários
public List<Usuario> listarTodosUsuarios() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Usuario";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("cargo")
                );
                usuarios.add(usuario);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return usuarios;
    }
}
