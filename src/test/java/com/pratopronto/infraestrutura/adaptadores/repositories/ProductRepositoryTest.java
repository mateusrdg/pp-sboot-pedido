package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.Product;
import com.pratopronto.dominio.enums.CategoryEnum;
import com.pratopronto.infraestrutura.adaptadores.entidades.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository productRepository;

    @Mock
    private ProductJpaRepository productJpaRepository;

    @Test
    void testeFindAll() {
        ProductEntity productEntity = new ProductEntity();
        when(productJpaRepository.findAll()).thenReturn(List.of(productEntity));

        List<Product> result = productRepository.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void testeFindProductsBySku() {
        String sku = "sku1";
        ProductEntity productEntity = new ProductEntity();
        when(productJpaRepository.findAllBySkuIn(List.of(sku))).thenReturn(List.of(productEntity));

        List<Product> result = productRepository.findProductsBySku(List.of(sku));

        assertEquals(1, result.size());
    }

    @Test
    void testeFindBySku() {
        String sku = "sku1";
        ProductEntity productEntity = new ProductEntity();
        when(productJpaRepository.findBySku(sku)).thenReturn(Optional.of(productEntity));

        Optional<Product> result = productRepository.findBySku(sku);

        assertTrue(result.isPresent());
    }

    @Test
    void testeFindByCategory() {
        CategoryEnum category = CategoryEnum.LANCHE;
        ProductEntity productEntity = new ProductEntity();
        when(productJpaRepository.findAllByCategory(category)).thenReturn(List.of(productEntity));

        List<Product> result = productRepository.findByCategory(category);

        assertEquals(1, result.size());
    }

    @Test
    void testeSaveProduct() {
        Product product = new Product();
        ProductEntity productEntity = new ProductEntity(product);
        when(productJpaRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        productRepository.save(product);

        verify(productJpaRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testeDeleteProduct() {
        String sku = "sku1";

        doNothing().when(productJpaRepository).deleteBySku(sku);

        productRepository.delete(sku);

        verify(productJpaRepository, times(1)).deleteBySku(sku);
    }
}
