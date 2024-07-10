package com.pratopronto.ordercommons;

import com.pratopronto.dominio.dtos.customer.CustomerDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderCommonCucumberStepDefinitions {

    @Autowired
    private OrderHttpClient orderHttpClient;

    private CustomerDTO customerDTO;

    private HttpStatus httpStatus;

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
        orderHttpClient.registerCustomer(customerDTO);
    }

    @When("I send a request to register the customer")
    public void iSendARequestToRegisterTheCustomer() {
        httpStatus = null;
        httpStatus = orderHttpClient.registerCustomer(customerDTO);
    }

    @Then("the customer is successfully registered")
    public void theCustomerIsSuccessfullyRegistered() {
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @When("I send a request to retrieve the customer by CPF {string}")
    public void iSendARequestToRetrieveTheCustomerByCpf(final String cpf) {
        customerDTO = orderHttpClient.getCustomer(cpf);
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
