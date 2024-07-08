package com.pratopronto.dominio.dtos.cliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteDTO {
    @NotNull(message = "O campo 'nome' não pode ser nulo")
    @NotBlank(message = "O campo 'nome' não pode estar em branco")
    private String nome;
    @NotNull(message = "O campo 'cpf' não pode ser nulo")
    @NotBlank(message = "O campo 'cpf' não pode estar em branco")
    private String cpf;
    public ClienteDTO(){
    }

    public ClienteDTO(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
