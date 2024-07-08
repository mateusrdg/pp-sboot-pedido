package com.pratopronto.dominio.portas.repositories;

import com.pratopronto.dominio.Cliente;

import java.util.Optional;

public interface ClienteRepositoryPort {

    Optional<Cliente> buscarPeloCpf(String cpf);

    void salvar(Cliente cliente);
}
