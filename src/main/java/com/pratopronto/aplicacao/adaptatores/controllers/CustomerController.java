package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.customer.CustomerDTO;
import com.pratopronto.dominio.portas.interfaces.CustomerServicePort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerServicePort customerServicePort;

    public CustomerController(CustomerServicePort customerServicePort) {
        this.customerServicePort = customerServicePort;
    }

    @PostMapping
    void registerCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        customerServicePort.registerCustomer(customerDTO);
    }

    @GetMapping(value = "/{cpf}")
    CustomerDTO findCustomerByCpf(@PathVariable String cpf) throws NotFoundException {
        return customerServicePort.findCustomerByCpf(cpf);
    }
}
