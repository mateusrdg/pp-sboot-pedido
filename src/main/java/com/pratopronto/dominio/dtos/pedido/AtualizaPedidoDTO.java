package com.pratopronto.dominio.dtos.pedido;

import com.pratopronto.dominio.Pedido;
import com.pratopronto.dominio.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AtualizaPedidoDTO {
    private UUID id;
    private StatusEnum status;
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    public AtualizaPedidoDTO() {
    }

    public AtualizaPedidoDTO(UUID id, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.dataAtualizacao = dataAtualizacao;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

}
