package com.pratopronto.dominio.portas.interfaces;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.product.ProductDTO;
import com.pratopronto.dominio.enums.CategoryEnum;

import java.util.List;

public interface ProductServicePort {

    List<ProductDTO> findProducts();

    List<ProductDTO> findProductsBySku(List<String> skus);

    void insert(ProductDTO productDTO);

    void update(String sku, ProductDTO productDTO) throws NotFoundException;

    void delete(String sku) throws NotFoundException;

    List<ProductDTO> findProductsByCategory(CategoryEnum categoryEnum) throws NotFoundException;
}
