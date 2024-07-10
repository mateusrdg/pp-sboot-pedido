package com.pratopronto.dominio;

import com.pratopronto.dominio.dtos.customer.CustomerDTO;

import java.util.UUID;

public class Customer {

    private UUID id;
    private String nome;
    private String cpf;

    public Customer() {
    }

    public Customer(UUID id, String nome, String cpf) {
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

    public Customer(CustomerDTO customerDTO) {
        this.nome = customerDTO.getName();
        this.cpf = customerDTO.getCpf();
    }

    public CustomerDTO toClienteDTO() {
        return new CustomerDTO(this.nome , this.cpf);
    }
}
