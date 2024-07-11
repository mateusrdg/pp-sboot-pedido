package com.pratopronto.dominio.adaptadores.services;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.Customer;
import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.Product;
import com.pratopronto.dominio.dtos.order.UpdateOrderDTO;
import com.pratopronto.dominio.dtos.order.OrderDTO;
import com.pratopronto.dominio.portas.interfaces.OrderServicePort;
import com.pratopronto.dominio.portas.repositories.CustomerRepositoryPort;
import com.pratopronto.dominio.portas.repositories.OrderRepositoryPort;
import com.pratopronto.dominio.portas.repositories.ProductRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImp implements OrderServicePort {

    private final OrderRepositoryPort orderRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;
    private final CustomerRepositoryPort customerRepositoryPort;

    public OrderServiceImp(OrderRepositoryPort orderRepositoryPort, ProductRepositoryPort productRepositoryPort, CustomerRepositoryPort customerRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) throws NotFoundException {
        Customer customer = validateCustomer(orderDTO.getCpfCliente());
        List<Product> products = validaProdutos(orderDTO.getProdutos());

        Order order = new Order(products, customer);
        return this.orderRepositoryPort.save(order).toPedidoDTO();
    }

    private List<Product> validaProdutos(List<String> listaSkus) {
        List<Product> products = productRepositoryPort.findProductsBySku(listaSkus);
        if (products.isEmpty())
            throw new NotFoundException("Produtos não existem");
        return products;
    }

    private Customer validateCustomer(String cpfCliente) {
        Optional<Customer> optionalCustomer = customerRepositoryPort.findCustomerByCpf(cpfCliente);
        if (optionalCustomer.isEmpty())
            throw new NotFoundException("Cliente não existe");
        return optionalCustomer.get();
    }

    @Override
    public List<OrderDTO> findOrders() {
        List<Order> orders = this.orderRepositoryPort.findAllTeste();
        return orders.stream().map(Order::toPedidoDTO).collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(Long id, UpdateOrderDTO updateOrderDTO) throws NotFoundException {
        Order order = validateOrder(id);
        update(order, updateOrderDTO);
        this.orderRepositoryPort.update(order);
    }

    private void update(Order order, UpdateOrderDTO updateOrderDTO) {
        order.setStatus(updateOrderDTO.getStatus());
    }

    private Order validateOrder(Long id) {
        Optional<Order> pedidoOptional =  this.orderRepositoryPort.findById(id);
        if (pedidoOptional.isEmpty())
            throw new NotFoundException("Pedido não existe");
        return pedidoOptional.get();
    }

    @Override
    public OrderDTO findOrderByCpf(String cpf) throws NotFoundException {
        Customer customer = validateCustomer(cpf);
        Optional<Order> pedidoOptional = this.orderRepositoryPort.findByCustomer(customer);
        return pedidoOptional.map(Order::toPedidoDTO).orElseThrow(() -> new NotFoundException("Pedido não existe"));
    }

}
