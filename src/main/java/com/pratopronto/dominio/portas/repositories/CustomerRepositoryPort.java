package com.pratopronto.dominio.portas.repositories;

import com.pratopronto.dominio.Customer;

import java.util.Optional;

public interface CustomerRepositoryPort {

    Optional<Customer> findCustomerByCpf(String cpf);

    void save(Customer customer);
}
