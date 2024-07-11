package com.pratopronto.infraestrutura.adaptadores.entidades;

import javax.persistence.*;


@Entity
@Table(name = "order_product")
public class OrderProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
