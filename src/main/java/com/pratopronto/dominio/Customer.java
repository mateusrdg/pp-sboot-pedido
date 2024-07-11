package com.pratopronto.dominio;

import com.pratopronto.dominio.dtos.customer.CustomerDTO;


public class Customer {

    private Long id;
    private String name;
    private String cpf;

    public Customer() {
    }

    public Customer(Long id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Customer(CustomerDTO customerDTO) {
        this.name = customerDTO.getName();
        this.cpf = customerDTO.getCpf();
    }

    public CustomerDTO toCustomerDTO() {
        return new CustomerDTO(this.name, this.cpf);
    }
}
