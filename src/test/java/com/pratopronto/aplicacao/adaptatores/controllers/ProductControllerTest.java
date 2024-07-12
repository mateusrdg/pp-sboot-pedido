package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.dominio.dtos.product.ProductDTO;
import com.pratopronto.dominio.portas.interfaces.ProductServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServicePort productServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testInsertProduct() throws Exception {

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Product1\", \"sku\": \"SKU123\", \"price\": 100.0 }"))
                .andExpect(status().isOk());

        verify(productServicePort, times(1)).insert(any(ProductDTO.class));
    }

    @Test
    void testGetProducts() throws Exception {
        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateProduct() throws Exception {

        mockMvc.perform(put("/products/SKU123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"UpdatedProduct\", \"price\": 150.0 }"))
                .andExpect(status().isOk());

        verify(productServicePort, times(1)).update(any(String.class), any(ProductDTO.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/SKU123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productServicePort, times(1)).delete(any(String.class));
    }

    @Test
    void testBuscarPorCategoria() throws Exception {
        mockMvc.perform(get("/products/categoria/lanche")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
