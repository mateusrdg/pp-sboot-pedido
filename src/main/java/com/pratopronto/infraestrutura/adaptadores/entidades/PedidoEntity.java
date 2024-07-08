package com.pratopronto.infraestrutura.adaptadores.entidades;

import com.pratopronto.dominio.Pedido;
import com.pratopronto.dominio.enums.StatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProdutoEntity> pedidoProdutos = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    public PedidoEntity() {
    }

    public PedidoEntity(UUID id, Pedido pedido) {
        this.id = id;
        this.pedidoProdutos = pedido.getProdutos().stream()
                .map(produto -> new PedidoProdutoEntity(this, new ProdutoEntity(produto)))
                .collect(Collectors.toList());
        this.cliente = new ClienteEntity(pedido.getCliente());
        this.status = pedido.getStatus();
        this.dataCriacao = pedido.getDataCriacao();
        this.dataAtualizacao = pedido.getDataAtualizacao();
    }
    public PedidoEntity(Pedido pedido) {
        this.pedidoProdutos = pedido.getProdutos().stream()
                .map(produto -> new PedidoProdutoEntity(this, new ProdutoEntity(produto)))
                .collect(Collectors.toList());
        this.cliente = new ClienteEntity(pedido.getCliente());
        this.status = pedido.getStatus();
        this.dataCriacao = pedido.getDataCriacao();
        this.dataAtualizacao = pedido.getDataAtualizacao();
    }

    public Pedido toPedido() {
        return new Pedido(this.id, this.pedidoProdutos.stream().map(PedidoProdutoEntity::getProduto)
                .map(ProdutoEntity::toProduto).collect(Collectors.toList()), this.cliente.toCliente(), this.status, this.dataCriacao, this.dataAtualizacao);
    }
}
