package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.Cliente;
import com.pratopronto.dominio.Pedido;
import com.pratopronto.dominio.portas.repositories.PedidoRepositoryPort;
import com.pratopronto.infraestrutura.adaptadores.entidades.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PedidoRepository implements PedidoRepositoryPort {

    private final SpringPedidoRepository springPedidoRepository;

    public PedidoRepository(SpringPedidoRepository springPedidoRepository) {
        this.springPedidoRepository = springPedidoRepository;
    }

    @Override
    public Optional<Pedido> buscarPeloCliente(Cliente cliente) {
        Optional<PedidoEntity> pedidoEntity = this.springPedidoRepository.findByCliente(cliente);
        return pedidoEntity.map(PedidoEntity::toPedido);
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        var pedidoEntity = new PedidoEntity(pedido);
        return this.springPedidoRepository.save(pedidoEntity).toPedido();
    }

    @Override
    public List<Pedido> buscarTodos() {
        List<PedidoEntity> pedidoEntities = this.springPedidoRepository.findPedidosPorStatusEDataCriacao();
        return pedidoEntities.stream().map(PedidoEntity::toPedido).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {
        return this.springPedidoRepository.findById(id).map(PedidoEntity::toPedido);
    }

    @Override
    public void atualizar(Pedido pedido) {
        PedidoEntity pedidoEntity = new PedidoEntity(pedido.getId(), pedido);
        this.springPedidoRepository.save(pedidoEntity);
    }
}
