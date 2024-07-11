package com.pratopronto.dominio.portas.repositories;

import com.pratopronto.dominio.Customer;
import com.pratopronto.dominio.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {

    Optional<Order> findByCustomer(Customer customer);

    Order save(Order order);

    List<Order> findAll();

    Optional<Order> findById(Long id);

    void update(Order order);

    List<Order> findAllTeste();
}
