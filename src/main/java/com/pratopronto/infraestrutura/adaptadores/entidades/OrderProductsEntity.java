package com.pratopronto.infraestrutura.adaptadores.entidades;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_products")
public class OrderProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private ProductEntity product;


    public OrderProductsEntity(OrderEntity order, ProductEntity product) {
        this.order = order;
        this.product = product;
    }

    public OrderProductsEntity() {

    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduto() {
        return product;
    }

    public void setProduto(ProductEntity produto) {
        this.product = produto;
    }
}
