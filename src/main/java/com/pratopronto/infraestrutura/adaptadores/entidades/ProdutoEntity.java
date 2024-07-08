package com.pratopronto.infraestrutura.adaptadores.entidades;

import com.pratopronto.dominio.Produto;
import com.pratopronto.dominio.enums.CategoriaEnum;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String sku;
    private String nome;
    private Double preco;
    private String descricao;
    private String imagens;
    private CategoriaEnum categoria;

    public ProdutoEntity() {
    }

    public ProdutoEntity(Produto produto) {
        this.id = produto.getId();
        this.sku = produto.getSku();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
        this.imagens = produto.getImagens();
        this.categoria = produto.getCategoria();
    }

    public void atualizar(Produto produto) {
        this.sku = produto.getSku();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
        this.imagens = produto.getImagens();
        this.categoria = produto.getCategoria();
    }

    public Produto toProduto() {
        return new Produto(this.id, this.sku, this.nome, this.descricao, this.preco, this.imagens, this.categoria);
    }

    public String getImagens() {
        return imagens;
    }

    public void setImagens(String imagens) {
        this.imagens = imagens;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }
}
