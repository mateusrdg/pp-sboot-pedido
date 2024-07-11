package com.pratopronto.ordercommons;

import com.pratopronto.dominio.dtos.order.OrderDTO;
import com.pratopronto.dominio.dtos.order.UpdateOrderDTO;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class OrderHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String ORDERS_ENDPOINT = "/orders";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String ordersEndpoint() {
        return SERVER_URL + ":" + port + ORDERS_ENDPOINT;
    }

    public OrderDTO getOrder(String cpf) {
        return restTemplate.getForEntity(ordersEndpoint() + "/" + cpf, OrderDTO.class).getBody();
    }

    public ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO) {
        return restTemplate.postForEntity(ordersEndpoint(), orderDTO, OrderDTO.class);
    }

    public HttpStatus updateOrder(Long id, UpdateOrderDTO updateOrderDTO) {
        restTemplate.put(ordersEndpoint() + "/" + id, updateOrderDTO);
        return HttpStatus.OK;
    }

    public OrderDTO[] getOrders() {
        return restTemplate.getForEntity(ordersEndpoint(), OrderDTO[].class).getBody();
    }
}
