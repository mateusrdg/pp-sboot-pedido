package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.dominio.dtos.cliente.ClienteDTO;
import com.pratopronto.dominio.portas.interfaces.ClienteServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class TesteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteServicePort clienteServicePort;

   /* @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(testeController).build();
    }*/

    /*@Test
    void testCadastrarCliente() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        // Configure clienteDTO com dados de teste

        mockMvc.perform(post("/testes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"John\", \"cpf\": \"12345678900\" }"))
                .andExpect(status().isOk());

        verify(clienteServicePort, times(1)).cadastraCliente(any(ClienteDTO.class));
    }

    @Test
    void testBuscarClientePorCpf() throws Exception {
        mockMvc.perform(get("/testes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Testee"));
    }*/
}
