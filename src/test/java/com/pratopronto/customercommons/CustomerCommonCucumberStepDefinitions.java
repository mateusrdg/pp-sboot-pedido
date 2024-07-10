package com.pratopronto.customercommons;

import com.pratopronto.dominio.dtos.customer.CustomerDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerCommonCucumberStepDefinitions {

    @Autowired
    private CustomerHttpClient customerHttpClient;

    private CustomerDTO customerDTO;

    @Given("I have the customer's data")
    public void iHaveTheCustomersData() {
        customerDTO = new CustomerDTO();
        customerDTO.setName("João");
        customerDTO.setCpf("12345678900");
    }

    @Given("I have a customer registered with CPF {string}")
    public void iHaveACustomerRegisteredWithCpf(final String cpf) {
        customerDTO = new CustomerDTO();
        customerDTO.setName("João");
        customerDTO.setCpf(cpf);
        customerHttpClient.registerCustomer(customerDTO);
    }

    @When("I send a request to register the customer")
    public void iSendARequestToRegisterTheCustomer() {
        customerHttpClient.registerCustomer(customerDTO);
    }

    @Then("the customer is successfully registered")
    public void theCustomerIsSuccessfullyRegistered() {
        HttpStatus httpStatus = customerHttpClient.getHttpStatus();
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @When("I send a request to retrieve the customer by CPF {string}")
    public void iSendARequestToRetrieveTheCustomerByCpf(final String cpf) {
        customerDTO = customerHttpClient.getCustomer(cpf);
    }

    @Then("I receive the customer's data")
    public void iReceiveTheCustomersData() {
        assertNotNull(customerDTO);
    }

    @And("the customer's name is {string}")
    public void theCustomersNameIs(String name) {
        assertEquals(name, customerDTO.getName());
    }
}
