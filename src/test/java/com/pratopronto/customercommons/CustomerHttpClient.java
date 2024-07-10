package com.pratopronto.customercommons;

import com.pratopronto.dominio.dtos.customer.CustomerDTO;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class CustomerHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String CUSTOMERS_ENDPOINT = "/customers";
    private HttpStatus httpStatus = null;

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();


    private String customersEndpoint() {
        return SERVER_URL + ":" + port + CUSTOMERS_ENDPOINT;
    }

    public int put(final String something) {
        return restTemplate.postForEntity(customersEndpoint(), something, Void.class).getStatusCodeValue();
    }

    public CustomerDTO getCustomer(String cpf) {
        return restTemplate.getForEntity(customersEndpoint() + "/" + cpf, CustomerDTO.class).getBody();
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void registerCustomer(CustomerDTO customerDTO) {
        httpStatus = restTemplate.postForEntity(customersEndpoint(), customerDTO, Void.class).getStatusCode();
    }

    public void clean() {
        httpStatus = null;
    }


}
