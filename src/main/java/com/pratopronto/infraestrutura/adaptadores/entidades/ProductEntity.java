package com.pratopronto.infraestrutura.adaptadores.entidades;

import com.pratopronto.dominio.Product;
import com.pratopronto.dominio.enums.CategoryEnum;

import javax.persistence.*;


@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sku;
    private String name;
    private Double price;
    private String description;
    private String images;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    public ProductEntity() {
    }

    public ProductEntity(Product product) {
        this.id = product.getId();
        this.sku = product.getSku();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.images = product.getImages();
        this.category = product.getCategory();
    }

    public void atualizar(Product product) {
        this.sku = product.getSku();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.images = product.getImages();
        this.category = product.getCategory();
    }

    public Product toProduto() {
        return new Product(this.id, this.sku, this.name, this.description, this.price, this.images, this.category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
