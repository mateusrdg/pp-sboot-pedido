package com.pratopronto.dominio;

import com.pratopronto.dominio.dtos.produto.ProdutoDTO;
import com.pratopronto.dominio.enums.CategoriaEnum;

import java.util.UUID;

public class Produto {

    private UUID id;
    private String sku;
    private String nome;
    private Double preco;
    private String descricao;
    private String imagens;
    private CategoriaEnum categoria;

    public Produto() {
    }

    public Produto(UUID id, String sku, String nome, String descricao, Double preco, String imagens, CategoriaEnum categoria) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagens = imagens;
        this.categoria = categoria;
    }

    public UUID getId() {
        return id;
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

    public String getDescricao() {
        return descricao;
    }

    public Produto(ProdutoDTO produtoDTO) {
        this.sku = produtoDTO.getSku();
        this.nome = produtoDTO.getNome();
        this.preco = produtoDTO.getPreco();
        this.descricao = produtoDTO.getDescricao();
        this.imagens = produtoDTO.getImagens();
        this.categoria = produtoDTO.getCategoria();
    }

    public void atualizar(ProdutoDTO produtoDTO) {
        this.nome = produtoDTO.getNome();
        this.preco = produtoDTO.getPreco();
        this.descricao = produtoDTO.getDescricao();
        this.imagens = produtoDTO.getImagens();
        this.categoria = produtoDTO.getCategoria();
    }
    public ProdutoDTO toProdutoDTO() {
        return new ProdutoDTO(this.sku, this.nome, this.preco, this.descricao, this.imagens, this.categoria);
    }
}
