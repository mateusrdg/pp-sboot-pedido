package com.pratopronto.dominio.portas.repositories;

import com.pratopronto.dominio.Product;
import com.pratopronto.dominio.enums.CategoryEnum;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    List<Product> findAll();

    List<Product> findProductsBySku(List<String> skus);

    Optional<Product> findBySku(String sku);

    List<Product> findByCategory(CategoryEnum categoria);

    void save(Product product);

    void delete(String sku);
}
