package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.dominio.dtos.order.OrderDTO;
import com.pratopronto.dominio.dtos.order.UpdateOrderDTO;
import com.pratopronto.dominio.portas.interfaces.OrderServicePort;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderServicePort orderServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testCheckout() throws Exception {

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"customerCpf\": \"12345678900\", \"items\": [] }"))
                .andExpect(status().isOk());

        verify(orderServicePort, times(1)).createOrder(any(OrderDTO.class));
    }

    @Test
    void testUpdateOrder() throws Exception {

        mockMvc.perform(put("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"status\": \"PRONTO\" }"))
                .andExpect(status().isOk());

        verify(orderServicePort, times(1)).updateOrderStatus(any(Long.class), any(UpdateOrderDTO.class));
    }

    @Test
    void testBuscarPedidoPorCpf() throws Exception {
        mockMvc.perform(get("/orders/12345678900")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPedidos() throws Exception {
        mockMvc.perform(get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
