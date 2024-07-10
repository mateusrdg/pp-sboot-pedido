package com.pratopronto.infraestrutura.adaptadores.entidades;

import com.pratopronto.dominio.Customer;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String cpf;

    public CustomerEntity() {
    }

    public CustomerEntity(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getNome();
        this.cpf = customer.getCpf();
    }

    public Customer toCustomer() {
        return new Customer(this.id, this.name, this.cpf);
    }
}
