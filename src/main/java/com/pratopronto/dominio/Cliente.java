package com.pratopronto.dominio;

import com.pratopronto.dominio.dtos.cliente.ClienteDTO;

import java.util.UUID;

public class Cliente {

    private UUID id;
    private String nome;
    private String cpf;

    public Cliente() {
    }

    public Cliente(UUID id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Cliente(ClienteDTO clienteDTO) {
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
    }

    public ClienteDTO toClienteDTO() {
        return new ClienteDTO(this.nome , this.cpf);
    }
}
