package com.pratopronto.infraestrutura.adaptadores.entidades;

import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.enums.StatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductsEntity> orderProducts = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    public OrderEntity() {
    }

    public OrderEntity(Long id, Order order) {
        this.id = id;
        this.orderProducts = order.getProducts().stream()
                .map(product -> new OrderProductsEntity(this, new ProductEntity(product)))
                .collect(Collectors.toList());
        this.customer = new CustomerEntity(order.getCliente());
        this.status = order.getStatus();
        this.createDateTime = order.getCreationDateTime();
        this.updateDateTime = order.getUpdateDateTime();
    }
    public OrderEntity(Order order) {
        this.orderProducts = order.getProducts().stream()
                .map(produto -> new OrderProductsEntity(this, new ProductEntity(produto)))
                .collect(Collectors.toList());
        this.customer = new CustomerEntity(order.getCliente());
        this.status = order.getStatus();
        this.createDateTime = order.getCreationDateTime();
        this.updateDateTime = order.getUpdateDateTime();
    }

    public Order toOrder() {
        return new Order(this.id, this.orderProducts.stream().map(OrderProductsEntity::getProduct)
                .map(ProductEntity::toProduto).collect(Collectors.toList()), this.customer.toCustomer(), this.status, this.createDateTime, this.updateDateTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderProductsEntity> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductsEntity> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
