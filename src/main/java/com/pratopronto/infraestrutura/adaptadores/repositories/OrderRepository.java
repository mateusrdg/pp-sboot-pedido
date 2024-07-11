package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.Customer;
import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.portas.repositories.OrderRepositoryPort;
import com.pratopronto.infraestrutura.adaptadores.entidades.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Component
public class OrderRepository implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepository(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Optional<Order> findByCustomer(Customer customer) {
        Optional<OrderEntity> pedidoEntity = this.orderJpaRepository.findByCustomerCpf(customer.getCpf());
        return pedidoEntity.map(OrderEntity::toOrder);
    }

    @Override
    public Order save(Order order) {
        var pedidoEntity = new OrderEntity(order);
        return this.orderJpaRepository.save(pedidoEntity).toOrder();
    }

    @Override
    public List<Order> findAll() {
        List<OrderEntity> pedidoEntities = this.orderJpaRepository.findOrdersByStatusAndCreateDateTime();
        return pedidoEntities.stream().map(OrderEntity::toOrder).collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findById(Long id) {
        this.orderJpaRepository.findAll();
        return this.orderJpaRepository.findById(id).map(OrderEntity::toOrder);
    }

    @Override
    public void update(Order order) {
        OrderEntity orderEntity = new OrderEntity(order.getId(), order);
        this.orderJpaRepository.save(orderEntity);
    }

    @Override
    public List<Order> findAllTeste() {
        List<OrderEntity> pedidoEntities = this.orderJpaRepository.findAll();
        return pedidoEntities.stream().map(OrderEntity::toOrder).collect(Collectors.toList());
    }
}
