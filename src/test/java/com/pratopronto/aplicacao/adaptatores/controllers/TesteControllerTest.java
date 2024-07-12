package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.dominio.dtos.customer.CustomerDTO;
import com.pratopronto.dominio.portas.interfaces.CustomerServicePort;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TesteControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerServicePort customerServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testCadastrarCliente() throws Exception {
        CustomerDTO clienteDTO = new CustomerDTO();
        // Configure clienteDTO com dados de teste

        mockMvc.perform(post("/testes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"John\", \"cpf\": \"12345678900\" }"))
                .andExpect(status().isOk());

        verify(customerServicePort, times(1)).registerCustomer(any(CustomerDTO.class));
    }

    @Test
    void testBuscarClientePorCpf() throws Exception {
        mockMvc.perform(get("/testes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Testee"));
    }
}
