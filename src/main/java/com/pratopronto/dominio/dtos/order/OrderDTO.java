package com.pratopronto.dominio.dtos.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pratopronto.dominio.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderDTO {
    private UUID id;
    private List<String> produtos;
    private String cpfCliente;
    private StatusEnum status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public OrderDTO() {
    }

    public OrderDTO(UUID id, List<String> produtos, String cpfCliente, StatusEnum status, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.produtos = produtos;
        this.cpfCliente = cpfCliente;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = produtos;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
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
