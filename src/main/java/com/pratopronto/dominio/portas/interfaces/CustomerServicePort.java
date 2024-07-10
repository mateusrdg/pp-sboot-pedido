package com.pratopronto.dominio.portas.interfaces;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.customer.CustomerDTO;

public interface CustomerServicePort {

    CustomerDTO findCustomerByCpf(String cpf)throws NotFoundException;

    void registerCustomer(CustomerDTO customerDTO);

}
