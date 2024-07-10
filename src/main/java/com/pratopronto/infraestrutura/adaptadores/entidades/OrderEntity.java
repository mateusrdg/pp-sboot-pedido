package com.pratopronto.infraestrutura.adaptadores.entidades;

import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.enums.StatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

    public OrderEntity(UUID id, Order order) {
        this.id = id;
        this.orderProducts = order.getProducts().stream()
                .map(produto -> new OrderProductsEntity(this, new ProductEntity(produto)))
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
        return new Order(this.id, this.orderProducts.stream().map(OrderProductsEntity::getProduto)
                .map(ProductEntity::toProduto).collect(Collectors.toList()), this.customer.toCustomer(), this.status, this.createDateTime, this.updateDateTime);
    }
}
