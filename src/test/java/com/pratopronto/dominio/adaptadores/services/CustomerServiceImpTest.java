package com.pratopronto.dominio.adaptadores.services;

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
import static org.mockito.Mockito.when;


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

}