package com.pratopronto.dominio.dtos.order;

import com.pratopronto.dominio.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateOrderDTO {
    private UUID id;
    private StatusEnum status;
    private LocalDateTime updateDate = LocalDateTime.now();

    public UpdateOrderDTO() {
    }

    public UpdateOrderDTO(UUID id, LocalDateTime updateDate) {
        this.id = id;
        this.updateDate = updateDate;
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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}
