package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.BadRequestException;
import com.pratopronto.dominio.Cliente;
import com.pratopronto.dominio.portas.repositories.ClienteRepositoryPort;
import com.pratopronto.infraestrutura.adaptadores.entidades.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRepository implements ClienteRepositoryPort {

    private final SpringClienteRepository springClienteRepository;

    public ClienteRepository(SpringClienteRepository springClienteRepository) {
        this.springClienteRepository = springClienteRepository;
    }


    @Override
    public Optional<Cliente> buscarPeloCpf(String cpf) {
        Optional<ClienteEntity> clienteEntity = this.springClienteRepository.findByCpf(cpf);
        return clienteEntity.map(ClienteEntity::toCliente);
    }

    @Override
    public void salvar(Cliente cliente) {
        this.springClienteRepository.findByCpf(cliente.getCpf())
                .ifPresent(clienteExistente -> {throw new BadRequestException("CPF já cadastrado");});
        this.springClienteRepository.save(new ClienteEntity(cliente));
    }
}
