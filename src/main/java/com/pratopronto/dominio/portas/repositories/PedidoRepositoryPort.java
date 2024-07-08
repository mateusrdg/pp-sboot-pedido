package com.pratopronto.dominio.portas.repositories;

import com.pratopronto.dominio.Cliente;
import com.pratopronto.dominio.Pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepositoryPort {

    Optional<Pedido> buscarPeloCliente(Cliente cliente);

    Pedido salvar(Pedido pedido);

    List<Pedido> buscarTodos();

    Optional<Pedido> buscarPorId(UUID id);

    void atualizar(Pedido pedido);
}
