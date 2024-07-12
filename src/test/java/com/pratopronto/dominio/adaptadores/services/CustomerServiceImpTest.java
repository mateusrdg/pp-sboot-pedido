package com.pratopronto.dominio.adaptadores.services;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.Customer;
import com.pratopronto.dominio.dtos.customer.CustomerDTO;
import com.pratopronto.dominio.portas.repositories.CustomerRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImpTest {

    @InjectMocks
    CustomerServiceImp customerServiceImp;

    @Mock
    private CustomerRepositoryPort customerRepositoryPort;

    @Test
    void testeConsultaPorId() {
        String cpf = "cpf";
        Customer customer = new Customer();
        customer.setCpf(cpf);
        when(customerRepositoryPort.findCustomerByCpf(cpf)).thenReturn(Optional.of(customer));

        CustomerDTO result = customerServiceImp.findCustomerByCpf(cpf);

        assertEquals(cpf, result.getCpf());
    }

    @Test
    void testeRegistrarCliente() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCpf("cpf");
        customerDTO.setName("John Doe");

        doNothing().when(customerRepositoryPort).save(any(Customer.class));

        customerServiceImp.registerCustomer(customerDTO);

        verify(customerRepositoryPort, times(1)).save(any(Customer.class));

    }

    @Test
    void testeConsultaPorIdNotFound() {
        String cpf = "cpf";
        when(customerRepositoryPort.findCustomerByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> customerServiceImp.findCustomerByCpf(cpf));
    }
}
