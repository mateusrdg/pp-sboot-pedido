package com.pratopronto.ordercommons;

import com.pratopronto.customercommons.CustomerHttpClient;
import com.pratopronto.dominio.dtos.customer.CustomerDTO;
import com.pratopronto.dominio.dtos.order.OrderDTO;
import com.pratopronto.dominio.dtos.order.UpdateOrderDTO;
import com.pratopronto.dominio.dtos.product.ProductDTO;
import com.pratopronto.dominio.enums.CategoryEnum;
import com.pratopronto.dominio.enums.StatusEnum;
import com.pratopronto.productcommons.ProductHttpClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderCommonCucumberStepDefinitions {

    @Autowired
    private OrderHttpClient orderHttpClient;

    @Autowired
    private CustomerHttpClient customerHttpClient;

    @Autowired
    private ProductHttpClient productHttpClient;

    private OrderDTO orderDTO;
    private UpdateOrderDTO updateOrderDTO;
    private HttpStatus httpStatus;
    private ResponseEntity<OrderDTO> responseEntity;

    @Given("I have the order's data")
    public void iHaveTheOrdersData() {
        customerHttpClient.registerCustomer(newCustomer("12345678999"));
        customerHttpClient.registerCustomer(newCustomer("12345678998"));
        productHttpClient.insertProduct(newProduct());
    }

    @Given("I have an order registered with CPF {string}")
    public void iHaveAnOrderRegisteredWithCpf(final String cpf) {
        orderDTO = new OrderDTO();
        orderDTO.setCpfCliente(cpf);
        orderDTO.setProdutos(List.of("123456abc"));
        responseEntity = orderHttpClient.createOrder(orderDTO);
    }

    @Given("I have an order")
    public void iHaveAnOrder() {
        updateOrderDTO = new UpdateOrderDTO();
        updateOrderDTO.setStatus(StatusEnum.PRONTO);
    }

    @When("I send a request to create the order")
    public void iSendARequestToCreateTheOrder() {
        orderDTO = new OrderDTO();
        orderDTO.setCpfCliente("12345678998");
        orderDTO.setProdutos(List.of("123456abc"));
        httpStatus = orderHttpClient.createOrder(orderDTO).getStatusCode();
    }

    @Then("the order is successfully created")
    public void theOrderIsSuccessfullyCreated() {
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @When("I send a request to retrieve the order by CPF {string}")
    public void iSendARequestToRetrieveTheOrderByCpf(final String cpf) {
        orderDTO = orderHttpClient.getOrder(cpf);
    }

    @Then("I receive the order's data")
    public void iReceiveTheOrdersData() {
        assertNotNull(orderDTO);
    }

    @When("I send a request to update the order status")
    public void iSendARequestToUpdateTheOrderStatus() {
        httpStatus = orderHttpClient.updateOrder(orderDTO.getId(), updateOrderDTO);
    }

    @Then("the order status is successfully updated")
    public void theOrderStatusIsSuccessfullyUpdated() {
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @When("I send a request to list all orders")
    public void iSendARequestToListAllOrders() {
        OrderDTO[] orders = orderHttpClient.getOrders();
        assertNotNull(orders);
        assertEquals(orders.length > 0, true);
    }

    @Then("I receive a list of all orders")
    public void iReceiveAListOfAllOrders() {
        OrderDTO[] orders = orderHttpClient.getOrders();
        assertNotNull(orders);
        assertEquals(orders.length > 0, true);
    }

    private ProductDTO newProduct() {
        return new ProductDTO(
                "123456abc",
                "Product 1",
                10.99,
                "Description for Product 1",
                "https://example.com/images/product1.jpg",
                CategoryEnum.LANCHE);
    }

    private CustomerDTO newCustomer(String number) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("João");
        customerDTO.setCpf(number);
        return customerDTO;
    }
}
