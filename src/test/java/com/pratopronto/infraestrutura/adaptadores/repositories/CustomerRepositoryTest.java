package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.BadRequestException;
import com.pratopronto.dominio.Customer;
import com.pratopronto.infraestrutura.adaptadores.entidades.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryTest {

    @InjectMocks
    private CustomerRepository customerRepository;

    @Mock
    private CustomerJpaRepository customerJpaRepository;

    @Test
    void testeFindCustomerByCpf() {
        String cpf = "cpf";
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCpf(cpf);
        when(customerJpaRepository.findByCpf(cpf)).thenReturn(Optional.of(customerEntity));

        Optional<Customer> result = customerRepository.findCustomerByCpf(cpf);

        assertTrue(result.isPresent());
        assertEquals(cpf, result.get().getCpf());
    }

    @Test
    void testeSaveCustomer() {
        Customer customer = new Customer();
        customer.setCpf("cpf");
        when(customerJpaRepository.findByCpf("cpf")).thenReturn(Optional.empty());

        customerRepository.save(customer);

        verify(customerJpaRepository, times(1)).save(any(CustomerEntity.class));
    }

    @Test
    void testeSaveCustomerCpfAlreadyExists() {
        Customer customer = new Customer();
        customer.setCpf("cpf");
        CustomerEntity existingCustomer = new CustomerEntity();
        when(customerJpaRepository.findByCpf("cpf")).thenReturn(Optional.of(existingCustomer));

        assertThrows(BadRequestException.class, () -> customerRepository.save(customer));
    }
}
