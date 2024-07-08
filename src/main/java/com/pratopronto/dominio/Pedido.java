package com.pratopronto.dominio;

import com.pratopronto.dominio.dtos.pedido.PedidoDTO;
import com.pratopronto.dominio.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Pedido {

    private UUID id;
    private List<Produto> produtos;
    private Cliente cliente;
    private StatusEnum status;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private LocalDateTime dataAtualizacao;
    public Pedido() {
    }

    public Pedido(UUID id, List<Produto> produtos, Cliente cliente, StatusEnum status, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.produtos = produtos;
        this.cliente = cliente;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Pedido(List<Produto> produtos, Cliente cliente) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.status = StatusEnum.RECEBIDO;
    }

    public PedidoDTO toPedidoDTO() {
        return new PedidoDTO(this.id, this.produtos.stream().map(Produto::getSku).collect(Collectors.toList()), this.cliente.getCpf(), this.status, this.dataCriacao, this.dataAtualizacao);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
