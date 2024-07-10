package com.pratopronto.dominio.dtos.product;

import com.pratopronto.dominio.enums.CategoryEnum;

public class ProductDTO {
    private String sku;
    private String nome;
    private Double preco;
    private String descricao;
    private String imagens;
    private CategoryEnum categoria;

    public ProductDTO(String sku, String nome, Double preco, String descricao, String imagens, CategoryEnum categoria) {
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

    public CategoryEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoryEnum categoria) {
        this.categoria = categoria;
    }

    public String getImagens() {
        return imagens;
    }

    public void setImagens(String imagens) {
        this.imagens = imagens;
    }
}
