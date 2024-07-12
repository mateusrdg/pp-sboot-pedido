package com.pratopronto.dominio;

import com.pratopronto.dominio.dtos.order.OrderDTO;
import com.pratopronto.dominio.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private Long id;
    private List<Product> products;
    private Customer customer;
    private StatusEnum status;
    private LocalDateTime creationDateTime = LocalDateTime.now();
    private LocalDateTime updateDateTime;
    public Order() {
    }

    public Order(Long id, List<Product> products, Customer customer, StatusEnum status, LocalDateTime creationDateTime, LocalDateTime updateDateTime) {
        this.id = id;
        this.products = products;
        this.customer = customer;
        this.status = status;
        this.creationDateTime = creationDateTime;
        this.updateDateTime = updateDateTime;
    }

    public Order(List<Product> products, Customer customer) {
        this.products = products;
        this.customer = customer;
        this.status = StatusEnum.RECEBIDO;
    }

    public OrderDTO toPedidoDTO() {
        return new OrderDTO(this.id, this.products.stream().map(Product::getSku).collect(Collectors.toList()), this.customer.getCpf(), this.status, this.creationDateTime, this.updateDateTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCliente() {
        return customer;
    }

    public void setCliente(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
