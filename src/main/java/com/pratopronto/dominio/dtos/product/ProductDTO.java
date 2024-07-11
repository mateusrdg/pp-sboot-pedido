package com.pratopronto.dominio.dtos.product;

import com.pratopronto.dominio.enums.CategoryEnum;

public class ProductDTO {

    private String sku;
    private String name;
    private Double price;
    private String description;
    private String images;
    private CategoryEnum category;

    public ProductDTO(){
    }

    public ProductDTO(String sku, String name, Double price, String description, String images, CategoryEnum category) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.description = description;
        this.images = images;
        this.category = category;
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

    public String getDescription() {
        return description;
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

    public void setSku(String sku) {
        this.sku = sku;
    }
}
