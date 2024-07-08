package com.pratopronto.dominio.adaptadores.services;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.Cliente;
import com.pratopronto.dominio.Pedido;
import com.pratopronto.dominio.Produto;
import com.pratopronto.dominio.dtos.pedido.AtualizaPedidoDTO;
import com.pratopronto.dominio.dtos.pedido.PedidoDTO;
import com.pratopronto.dominio.portas.interfaces.PedidoServicePort;
import com.pratopronto.dominio.portas.repositories.ClienteRepositoryPort;
import com.pratopronto.dominio.portas.repositories.PedidoRepositoryPort;
import com.pratopronto.dominio.portas.repositories.ProdutoRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoServiceImp implements PedidoServicePort {

    private final PedidoRepositoryPort pedidoRepositoryPort;
    private final ProdutoRepositoryPort produtoRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;

    public PedidoServiceImp(PedidoRepositoryPort pedidoRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort, ClienteRepositoryPort clienteRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
        this.produtoRepositoryPort = produtoRepositoryPort;
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public PedidoDTO criaPedido(PedidoDTO pedidoDTO) throws NotFoundException {
        Cliente cliente = validaCliente(pedidoDTO.getCpfCliente());
        List<Produto> produtos = validaProdutos(pedidoDTO.getProdutos());

        Pedido pedido = new Pedido(produtos, cliente);
        return this.pedidoRepositoryPort.salvar(pedido).toPedidoDTO();
    }

    private List<Produto> validaProdutos(List<String> listaSkus) {
        List<Produto> produtos = produtoRepositoryPort.buscarProdutosPorSku(listaSkus);
        if (produtos.isEmpty())
            throw new NotFoundException("Produtos não existem");
        return produtos;
    }

    private Cliente validaCliente(String cpfCliente) {
        Optional<Cliente> clienteOptional = clienteRepositoryPort.buscarPeloCpf(cpfCliente);
        if (clienteOptional.isEmpty())
            throw new NotFoundException("Cliente não existe");
        return clienteOptional.get();
    }

    @Override
    public List<PedidoDTO> buscarPedidos() {
        List<Pedido> pedidos = this.pedidoRepositoryPort.buscarTodos();
        return pedidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }

    @Override
    public void atualizaStatus(UUID id, AtualizaPedidoDTO atualizaPedidoDTO) throws NotFoundException {
        Pedido pedido = validaPedido(id);
        atualizar(pedido, atualizaPedidoDTO);
        this.pedidoRepositoryPort.atualizar(pedido);
    }

    private void atualizar(Pedido pedido, AtualizaPedidoDTO atualizaPedidoDTO) {
        pedido.setStatus(atualizaPedidoDTO.getStatus());
    }

    private Pedido validaPedido(UUID id) {
        Optional<Pedido> pedidoOptional =  this.pedidoRepositoryPort.buscarPorId(id);
        if (pedidoOptional.isEmpty())
            throw new NotFoundException("Pedido não existe");
        return pedidoOptional.get();
    }

    @Override
    public PedidoDTO buscarPedidoPorCpf(String cpf) throws NotFoundException {
        Cliente cliente = validaCliente(cpf);
        Optional<Pedido> pedidoOptional = this.pedidoRepositoryPort.buscarPeloCliente(cliente);
        return pedidoOptional.map(Pedido::toPedidoDTO).orElseThrow(() -> new NotFoundException("Pedido não existe"));
    }

}
