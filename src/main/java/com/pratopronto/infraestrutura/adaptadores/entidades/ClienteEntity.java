package com.pratopronto.infraestrutura.adaptadores.entidades;

import com.pratopronto.dominio.Cliente;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "clientes")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String cpf;

    public ClienteEntity() {
    }

    public ClienteEntity(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
    }

    public Cliente toCliente() {
        return new Cliente(this.id, this.nome, this.cpf);
    }
}
