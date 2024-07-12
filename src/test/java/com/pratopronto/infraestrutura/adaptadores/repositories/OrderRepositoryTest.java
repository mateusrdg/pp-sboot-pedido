package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.Customer;
import com.pratopronto.dominio.Order;
import com.pratopronto.infraestrutura.adaptadores.entidades.CustomerEntity;
import com.pratopronto.infraestrutura.adaptadores.entidades.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryTest {

    @InjectMocks
    private OrderRepository orderRepository;

    @Mock
    private OrderJpaRepository orderJpaRepository;

    @Test
    void testeFindByCustomer() {
        String cpf = "cpf";
        Customer customer = new Customer();
        customer.setCpf(cpf);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCpf(cpf);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customerEntity);
        when(orderJpaRepository.findByCustomerCpf(cpf)).thenReturn(Optional.of(orderEntity));

        Optional<Order> result = orderRepository.findByCustomer(customer);

        assertTrue(result.isPresent());
    }

    @Test
    void testeSaveOrder() {
        Order order = new Order();
        order.setProducts(new ArrayList<>());
        Customer customer = new Customer();
        order.setCustomer(customer);
        OrderEntity orderEntity = new OrderEntity(order);
        when(orderJpaRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        Order result = orderRepository.save(order);

        assertNotNull(result);
    }

    @Test
    void testeFindAll() {
        CustomerEntity customerEntity = new CustomerEntity();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customerEntity);
        when(orderJpaRepository.findOrdersByStatusAndCreateDateTime()).thenReturn(List.of(orderEntity));

        List<Order> result = orderRepository.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    void testeFindById() {
        Long id = 1L;
        CustomerEntity customerEntity = new CustomerEntity();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customerEntity);
        when(orderJpaRepository.findById(id)).thenReturn(Optional.of(orderEntity));

        Optional<Order> result = orderRepository.findById(id);

        assertTrue(result.isPresent());
    }

    @Test
    void testeUpdateOrder() {
        Order order = new Order();
        order.setProducts(new ArrayList<>());
        Customer customer = new Customer();
        order.setCustomer(customer);
        OrderEntity orderEntity = new OrderEntity(order.getId(), order);
        when(orderJpaRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        orderRepository.update(order);

        verify(orderJpaRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void testeFindAllTeste() {
        CustomerEntity customerEntity = new CustomerEntity();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customerEntity);
        when(orderJpaRepository.findAll()).thenReturn(List.of(orderEntity));

        List<Order> result = orderRepository.findAllTeste();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }
}
