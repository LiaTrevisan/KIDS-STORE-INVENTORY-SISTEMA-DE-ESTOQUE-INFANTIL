/**
 * Classe que representa um produto infantil (brinquedo ou livro)
 *
 * @author lia
 */
package Model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String categoria; // "Brinquedo" ou "Livro"
    private String faixaEtaria; // Ex: "3-5 anos", "6-8 anos"
    private String descricao;
    private int estoqueMinimo;

    // Construtor completo
    public Produto(String nome, double preco, String categoria, String faixaEtaria, String descricao, int estoqueMinimo) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.faixaEtaria = faixaEtaria;
        this.descricao = descricao;
        this.estoqueMinimo = estoqueMinimo;
    }

    // Construtor simples (para compatibilidade)
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = "Brinquedo";
        this.faixaEtaria = "3-5 anos";
        this.descricao = "";
        this.estoqueMinimo = 10;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }
}


