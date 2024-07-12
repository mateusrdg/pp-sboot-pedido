package com.pratopronto.dominio;

import com.pratopronto.dominio.dtos.product.ProductDTO;
import com.pratopronto.dominio.enums.CategoryEnum;


public class Product {

    private Long id;
    private String sku;
    private String name;
    private Double price;
    private String description;
    private String images;
    private CategoryEnum category;

    public Product() {
    }

    public Product(Long id, String sku, String name, String description, Double price, String images, CategoryEnum category) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.description = description;
        this.images = images;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Product(ProductDTO productDTO) {
        this.sku = productDTO.getSku();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.description = productDTO.getDescription();
        this.images = productDTO.getImages();
        this.category = productDTO.getCategory();
    }

    public void atualizar(ProductDTO productDTO) {
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.description = productDTO.getDescription();
        this.images = productDTO.getImages();
        this.category = productDTO.getCategory();
    }
    public ProductDTO toProductDTO() {
        return new ProductDTO(this.sku, this.name, this.price, this.description, this.images, this.category);
    }
}
