package com.pratopronto.dominio.dtos.order;

import com.pratopronto.dominio.enums.StatusEnum;

import java.time.LocalDateTime;

public class UpdateOrderDTO {
    private Long id;
    private StatusEnum status;
    private LocalDateTime updateDate = LocalDateTime.now();

    public UpdateOrderDTO() {
    }

    public UpdateOrderDTO(Long id, LocalDateTime updateDate) {
        this.id = id;
        this.updateDate = updateDate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
