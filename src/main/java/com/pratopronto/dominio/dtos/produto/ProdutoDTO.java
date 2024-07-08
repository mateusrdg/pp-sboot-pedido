package com.pratopronto.dominio.dtos.produto;

import com.pratopronto.dominio.enums.CategoriaEnum;

public class ProdutoDTO {
    private String sku;
    private String nome;
    private Double preco;
    private String descricao;
    private String imagens;
    private CategoriaEnum categoria;

    public ProdutoDTO(String sku, String nome, Double preco, String descricao, String imagens, CategoriaEnum categoria) {
        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagens = imagens;
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public String getImagens() {
        return imagens;
    }

    public void setImagens(String imagens) {
        this.imagens = imagens;
    }
}
