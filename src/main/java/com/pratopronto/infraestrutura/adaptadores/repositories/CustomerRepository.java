package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.BadRequestException;
import com.pratopronto.dominio.Customer;
import com.pratopronto.dominio.portas.repositories.CustomerRepositoryPort;
import com.pratopronto.infraestrutura.adaptadores.entidades.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepository implements CustomerRepositoryPort {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepository(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Optional<Customer> findCustomerByCpf(String cpf) {
        Optional<CustomerEntity> customerEntity = this.customerJpaRepository.findByCpf(cpf);
        return customerEntity.map(CustomerEntity::toCustomer);
    }

    @Override
    public void save(Customer customer) {
        this.customerJpaRepository.findByCpf(customer.getCpf())
                .ifPresent(customerExists -> {throw new BadRequestException("CPF já cadastrado");});
        this.customerJpaRepository.save(new CustomerEntity(customer));
    }
}
