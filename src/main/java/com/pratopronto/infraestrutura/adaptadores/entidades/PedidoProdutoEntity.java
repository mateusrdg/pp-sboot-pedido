package com.pratopronto.infraestrutura.adaptadores.entidades;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "pedido_produto")
public class PedidoProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;


    public PedidoProdutoEntity(PedidoEntity pedido, ProdutoEntity produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    public PedidoProdutoEntity() {

    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }
}
