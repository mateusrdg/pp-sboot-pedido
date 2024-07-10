package com.pratopronto.dominio.dtos.customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerDTO {
    @NotNull(message = "O campo 'nome' não pode ser nulo")
    @NotBlank(message = "O campo 'nome' não pode estar em branco")
    private String name;
    @NotNull(message = "O campo 'cpf' não pode ser nulo")
    @NotBlank(message = "O campo 'cpf' não pode estar em branco")
    private String cpf;
    public CustomerDTO(){
    }

    public CustomerDTO(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
