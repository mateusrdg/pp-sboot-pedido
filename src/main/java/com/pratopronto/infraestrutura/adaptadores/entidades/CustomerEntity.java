package com.pratopronto.infraestrutura.adaptadores.entidades;

import com.pratopronto.dominio.Customer;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String cpf;

    public CustomerEntity() {
    }

    public CustomerEntity(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
    }

    public Customer toCustomer() {
        return new Customer(this.id, this.name, this.cpf);
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
}
