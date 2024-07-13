package com.pratopronto.dominio.adaptadores.services;

import com.pratopronto.dominio.Customer;
import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.Product;
import com.pratopronto.dominio.dtos.order.OrderDTO;
import com.pratopronto.dominio.dtos.order.UpdateOrderDTO;
import com.pratopronto.dominio.portas.interfaces.PaymentServicePort;
import com.pratopronto.dominio.portas.repositories.CustomerRepositoryPort;
import com.pratopronto.dominio.portas.repositories.OrderRepositoryPort;
import com.pratopronto.dominio.portas.repositories.ProductRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.pratopronto.dominio.enums.StatusEnum.PRONTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImpTest {

    @InjectMocks
    OrderServiceImp orderServiceImp;

    @Mock
    private OrderRepositoryPort orderRepositoryPort;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private CustomerRepositoryPort customerRepositoryPort;

    @Mock
    private PaymentServicePort paymentServicePort;

    @Test
    void testeCreateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCpfCliente("cpf");
        orderDTO.setProdutos(List.of("sku1", "sku2"));

        Customer customer = new Customer();
        customer.setCpf("cpf");
        when(customerRepositoryPort.findCustomerByCpf("cpf")).thenReturn(Optional.of(customer));

        Product product1 = new Product();
        product1.setSku("sku1");
        Product product2 = new Product();
        product2.setSku("sku2");
        when(productRepositoryPort.findProductsBySku(List.of("sku1", "sku2"))).thenReturn(List.of(product1, product2));

        Order order = new Order(List.of(product1, product2), customer);
        when(orderRepositoryPort.save(any(Order.class))).thenReturn(order);

        OrderDTO result = orderServiceImp.createOrder(orderDTO);

        assertEquals(order.toOrderDTO().getId(), result.getId());
    }

    @Test
    void testeFindOrders() {
        var order = new Order();
        var product = new Product();
        var customer = new Customer();
        product.setSku("123");
        customer.setCpf("60541329388");
        order.setCliente(customer);
        order.setProducts(List.of(product));
        when(orderRepositoryPort.findAllTeste()).thenReturn(List.of(order));

        List<OrderDTO> result = orderServiceImp.findOrders();

        assertEquals(1, result.size());
        assertEquals(order.toOrderDTO().getId(), result.get(0).getId());
    }

    @Test
    void testeUpdateOrderStatus() {
        Long id = 1L;
        UpdateOrderDTO updateOrderDTO = new UpdateOrderDTO();
        updateOrderDTO.setStatus(PRONTO);

        Order order = new Order();
        when(orderRepositoryPort.findById(id)).thenReturn(Optional.of(order));

        doNothing().when(orderRepositoryPort).update(any(Order.class));

        orderServiceImp.updateOrderStatus(id, updateOrderDTO);

        verify(orderRepositoryPort, times(1)).update(order);
        assertEquals(PRONTO, order.getStatus());
    }

    @Test
    void testeFindOrderByCpf() {
        var order = new Order();
        var product = new Product();
        var customer = new Customer();
        product.setSku("123");
        customer.setCpf("60541329388");
        order.setCliente(customer);
        order.setProducts(List.of(product));

        when(customerRepositoryPort.findCustomerByCpf("60541329388")).thenReturn(Optional.of(customer));
        when(orderRepositoryPort.findByCustomer(customer)).thenReturn(Optional.of(order));

        OrderDTO result = orderServiceImp.findOrderByCpf("60541329388");

        assertEquals(order.toOrderDTO().getCpfCliente(), result.getCpfCliente());
    }
}
