/*
 * DAO responsável pelas operações de CRUD de Produto
 * Princípios SOLID aplicados:
 * - SRP: Responsável apenas por operações de banco de dados de Produto
 * - OCP: Aberto para extensão através de novos métodos
 */
package Control;

import Model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para produtos infantis (brinquedos e livros)
 *
 * @author lia
 */
public class ProdutoDAO {

    public void inserirProduto(Produto produto) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "INSERT INTO Produto (nome, preco, categoria, faixa_etaria, descricao, estoque_minimo) VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setString(3, produto.getCategoria());
            ps.setString(4, produto.getFaixaEtaria());
            ps.setString(5, produto.getDescricao());
            ps.setInt(6, produto.getEstoqueMinimo());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao inserir produto infantil: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para atualizar um produto
    public void atualizarProduto(Produto produto) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "UPDATE Produto SET nome = ?, preco = ?, categoria = ?, faixa_etaria = ?, descricao = ?, estoque_minimo = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setString(3, produto.getCategoria());
            ps.setString(4, produto.getFaixaEtaria());
            ps.setString(5, produto.getDescricao());
            ps.setInt(6, produto.getEstoqueMinimo());
            ps.setInt(7, produto.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para deletar um produto
    public void deletarProduto(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "DELETE FROM Produto WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para buscar um produto por ID
    public Produto buscarProdutoPorId(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Produto produto = null;

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Produto WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                produto = new Produto(
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getString("faixa_etaria"),
                    rs.getString("descricao"),
                    rs.getInt("estoque_minimo")
                );
                produto.setId(rs.getInt("id"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar produto por ID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return produto;
    }

    // Método para buscar todos os produtos
    public List<Produto> buscarTodosProdutos() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Produto";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getString("faixa_etaria"),
                    rs.getString("descricao"),
                    rs.getInt("estoque_minimo")
                );
                produto.setId(rs.getInt("id"));
                produtos.add(produto);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar todos os produtos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return produtos;
    }

    // Método para buscar produtos por nome
    public List<Produto> buscarProdutosPorNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Produto WHERE nome LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getString("faixa_etaria"),
                    rs.getString("descricao"),
                    rs.getInt("estoque_minimo")
                );
                produto.setId(rs.getInt("id"));
                produtos.add(produto);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar produtos por nome: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return produtos;
    }

    // Método para buscar produtos por categoria (Brinquedo ou Livro)
    public List<Produto> buscarProdutosPorCategoria(String categoria) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Produto WHERE categoria = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, categoria);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getString("faixa_etaria"),
                    rs.getString("descricao"),
                    rs.getInt("estoque_minimo")
                );
                produto.setId(rs.getInt("id"));
                produtos.add(produto);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar produtos por categoria: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return produtos;
    }

    // Método para buscar produtos por faixa etária
    public List<Produto> buscarProdutosPorFaixaEtaria(String faixaEtaria) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            conn = ConexaoBD.getConexao();
            String sql = "SELECT * FROM Produto WHERE faixa_etaria = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, faixaEtaria);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getString("faixa_etaria"),
                    rs.getString("descricao"),
                    rs.getInt("estoque_minimo")
                );
                produto.setId(rs.getInt("id"));
                produtos.add(produto);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar produtos por faixa etária: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConexaoBD.fecharConexao(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return produtos;
    }
}
