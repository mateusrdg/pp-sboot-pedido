package com.pratopronto.dominio.portas.interfaces;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.order.UpdateOrderDTO;
import com.pratopronto.dominio.dtos.order.OrderDTO;

import java.util.List;

public interface OrderServicePort {
    OrderDTO findOrderByCpf(String cpf)throws NotFoundException;
    OrderDTO createOrder(OrderDTO orderDTO) throws NotFoundException;
    List<OrderDTO> findOrders();
    void updateOrderStatus(Long id, UpdateOrderDTO updateOrderDTO)throws NotFoundException;

}
