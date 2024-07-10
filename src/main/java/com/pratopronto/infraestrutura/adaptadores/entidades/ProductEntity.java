package com.pratopronto.infraestrutura.adaptadores.entidades;

import com.pratopronto.dominio.Product;
import com.pratopronto.dominio.enums.CategoryEnum;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String sku;
    private String name;
    private Double price;
    private String description;
    private String images;
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

    public String getImages() {
        return images;
    }

    public void setImages(String imagens) {
        this.images = imagens;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum categoria) {
        this.category = categoria;
    }
}
