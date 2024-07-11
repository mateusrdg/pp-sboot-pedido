package com.pratopronto.dominio.adaptadores.services;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.Customer;
import com.pratopronto.dominio.dtos.customer.CustomerDTO;
import com.pratopronto.dominio.portas.interfaces.CustomerServicePort;
import com.pratopronto.dominio.portas.repositories.CustomerRepositoryPort;

import java.util.Optional;

public class CustomerServiceImp implements CustomerServicePort {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CustomerServiceImp(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public void registerCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO);
        this.customerRepositoryPort.save(customer);
    }

    @Override
    public CustomerDTO findCustomerByCpf(String cpf) {
        Optional<Customer> customer = this.customerRepositoryPort.findCustomerByCpf(cpf);
        return customer.map(Customer::toCustomerDTO).orElseThrow(() -> new NotFoundException("Cliente não existe"));
    }

}
