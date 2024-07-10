package com.pratopronto.ordercommons;

import com.pratopronto.dominio.dtos.customer.CustomerDTO;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class OrderHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String CUSTOMERS_ENDPOINT = "/orders";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();


    private String customersEndpoint() {
        return SERVER_URL + ":" + port + CUSTOMERS_ENDPOINT;
    }

    public CustomerDTO getCustomer(String cpf) {
        return restTemplate.getForEntity(customersEndpoint() + "/" + cpf, CustomerDTO.class).getBody();
    }

    public HttpStatus registerCustomer(CustomerDTO customerDTO) {
        return restTemplate.postForEntity(customersEndpoint(), customerDTO, Void.class).getStatusCode();
    }

}
