package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.pedido.AtualizaPedidoDTO;
import com.pratopronto.dominio.dtos.pedido.PedidoDTO;
import com.pratopronto.dominio.portas.interfaces.PedidoServicePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    private final PedidoServicePort pedidoServicePort;

    public PedidoController(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }

    @PostMapping
    public PedidoDTO checkout(@RequestBody PedidoDTO pedidoDTO)throws NotFoundException {
        return pedidoServicePort.criaPedido(pedidoDTO);
    }

    @PutMapping(value = "/{id}")
    public void atualizarStatus(@PathVariable UUID id, @RequestBody AtualizaPedidoDTO atualizaPedidoDTO)throws NotFoundException {
        pedidoServicePort.atualizaStatus(id, atualizaPedidoDTO);
    }

    @GetMapping(value = "/{cpf}")
    PedidoDTO buscarPedidoPorCpf(@PathVariable String cpf) throws NotFoundException {
        return pedidoServicePort.buscarPedidoPorCpf(cpf);
    }

    @GetMapping()
    List<PedidoDTO> getPedidos() {
        return pedidoServicePort.buscarPedidos();
    }
}
