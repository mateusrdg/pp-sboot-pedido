package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.order.UpdateOrderDTO;
import com.pratopronto.dominio.dtos.order.OrderDTO;
import com.pratopronto.dominio.portas.interfaces.OrderServicePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderServicePort orderServicePort;

    public OrderController(OrderServicePort orderServicePort) {
        this.orderServicePort = orderServicePort;
    }

    @PostMapping
    public OrderDTO checkout(@RequestBody OrderDTO orderDTO)throws NotFoundException {
        return orderServicePort.createOrder(orderDTO);
    }

    @PutMapping(value = "/{id}")
    public void updateOrder(@PathVariable UUID id, @RequestBody UpdateOrderDTO updateOrderDTO)throws NotFoundException {
        orderServicePort.updateOrderStatus(id, updateOrderDTO);
    }

    @GetMapping(value = "/{cpf}")
    OrderDTO buscarPedidoPorCpf(@PathVariable String cpf) throws NotFoundException {
        return orderServicePort.findOrderByCpf(cpf);
    }

    @GetMapping()
    List<OrderDTO> getPedidos() {
        return orderServicePort.findOrders();
    }
}
