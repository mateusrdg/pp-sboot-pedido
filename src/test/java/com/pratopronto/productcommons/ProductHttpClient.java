package com.pratopronto.productcommons;

import com.pratopronto.dominio.dtos.product.ProductDTO;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ProductHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String PRODUCTS_ENDPOINT = "/products";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String productsEndpoint() {
        return SERVER_URL + ":" + port + PRODUCTS_ENDPOINT;
    }

    public ProductDTO getProduct(String sku) {
        return restTemplate.getForEntity(productsEndpoint() + "/" + sku, ProductDTO.class).getBody();
    }

    public HttpStatus insertProduct(ProductDTO productDTO) {
        return restTemplate.postForEntity(productsEndpoint(), productDTO, Void.class).getStatusCode();
    }

    public HttpStatus updateProduct(String sku, ProductDTO productDTO) {
        restTemplate.put(productsEndpoint() + "/" + sku, productDTO);
        return HttpStatus.OK;
    }

    public HttpStatus deleteProduct(String sku) {
        restTemplate.delete(productsEndpoint() + "/" + sku);
        return HttpStatus.OK;
    }

    public ProductDTO[] getProductsByCategory(String category) {
        return restTemplate.getForEntity(productsEndpoint() + "/categoria/" + category, ProductDTO[].class).getBody();
    }
}
