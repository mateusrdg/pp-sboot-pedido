package com.pratopronto.dominio.adaptadores.services;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.BadRequestException;
import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.Product;
import com.pratopronto.dominio.dtos.product.ProductDTO;
import com.pratopronto.dominio.enums.CategoryEnum;
import com.pratopronto.dominio.portas.repositories.ProductRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImpTest {

    @InjectMocks
    ProductServiceImp productServiceImp;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Test
    void testeInsertProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku("sku1");

        when(productRepositoryPort.findBySku("sku1")).thenReturn(Optional.empty());

        doNothing().when(productRepositoryPort).save(any(Product.class));

        productServiceImp.insert(productDTO);

        verify(productRepositoryPort, times(1)).save(any(Product.class));
    }

    @Test
    void testeInsertProductSkuAlreadyExists() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku("sku1");

        Product existingProduct = new Product();
        when(productRepositoryPort.findBySku("sku1")).thenReturn(Optional.of(existingProduct));

        assertThrows(BadRequestException.class, () -> productServiceImp.insert(productDTO));
    }

    @Test
    void testeFindProducts() {
        Product product = new Product();
        when(productRepositoryPort.findAll()).thenReturn(List.of(product));

        List<ProductDTO> result = productServiceImp.findProducts();

        assertEquals(1, result.size());
        assertEquals(product.toProductDTO().getSku(), result.get(0).getSku());
    }

    @Test
    void testeUpdateProduct() {
        String sku = "sku1";
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(sku);

        Product existingProduct = new Product();
        when(productRepositoryPort.findBySku(sku)).thenReturn(Optional.of(existingProduct));

        doNothing().when(productRepositoryPort).save(any(Product.class));

        productServiceImp.update(sku, productDTO);

        verify(productRepositoryPort, times(1)).save(any(Product.class));
    }

    @Test
    void testeUpdateProductNotFound() {
        String sku = "sku1";
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(sku);

        when(productRepositoryPort.findBySku(sku)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productServiceImp.update(sku, productDTO));
    }

    @Test
    void testeDeleteProduct() {
        String sku = "sku1";

        Product existingProduct = new Product();
        when(productRepositoryPort.findBySku(sku)).thenReturn(Optional.of(existingProduct));

        doNothing().when(productRepositoryPort).delete(sku);

        productServiceImp.delete(sku);

        verify(productRepositoryPort, times(1)).delete(sku);
    }

    @Test
    void testeDeleteProductNotFound() {
        String sku = "sku1";

        when(productRepositoryPort.findBySku(sku)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productServiceImp.delete(sku));
    }

    @Test
    void testeFindProductsByCategory() {
        CategoryEnum category = CategoryEnum.LANCHE;

        Product product = new Product();
        when(productRepositoryPort.findByCategory(category)).thenReturn(List.of(product));

        List<ProductDTO> result = productServiceImp.findProductsByCategory(category);

        assertEquals(1, result.size());
        assertEquals(product.toProductDTO().getSku(), result.get(0).getSku());
    }
}
