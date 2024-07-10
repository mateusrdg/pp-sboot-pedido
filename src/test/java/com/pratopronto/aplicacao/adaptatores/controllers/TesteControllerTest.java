package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.dominio.portas.interfaces.CustomerServicePort;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class TesteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerServicePort customerServicePort;

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
