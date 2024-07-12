package com.pratopronto.aplicacao.adaptatores.controllers.exception;

import com.pratopronto.aplicacao.adaptatores.controllers.CustomerController;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomExceptionHandlerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerServicePort customerServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    void testHandleBadRequestException() throws Exception {
        doThrow(new BadRequestException("Bad Request"))
                .when(customerServicePort)
                .registerCustomer(any(CustomerDTO.class));


        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John\", \"cpf\": \"12345678900\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request"));
    }

    @Test
    void testHandleNotFoundException() throws Exception {
        when(customerServicePort.findCustomerByCpf(any(String.class)))
                .thenThrow(new NotFoundException("Customer not found"));

        mockMvc.perform(get("/customers/12345678900")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found"));
    }

    @Test
    void testHandleMethodArgumentNotValidException() throws Exception {
        // Simulate validation failure
        CustomerDTO invalidCustomer = new CustomerDTO();
        invalidCustomer.setName(""); // Nome vazio para simular falha de validação
        invalidCustomer.setCpf("12345678900");

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"\", \"cpf\": \"12345678900\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("O campo 'nome' não pode estar em branco"));
    }
}
