package com.pratopronto.productcommons;

import com.pratopronto.dominio.dtos.product.ProductDTO;
import com.pratopronto.dominio.enums.CategoryEnum;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductCommonCucumberStepDefinitions {

    @Autowired
    private ProductHttpClient productHttpClient;

    private ProductDTO productDTO;
    private HttpStatus httpStatus;

    @Given("I have the product's data")
    public void iHaveTheProductsData() {
        productDTO = newProduct("12345");
    }

    @Given("I have a product registered with SKU {string}")
    public void iHaveAProductRegisteredWithSku(final String sku) {
        productDTO = newProduct(sku);
        productDTO.setSku(sku);
        productHttpClient.insertProduct(productDTO);
    }

    @Given("I have a product with SKU {string}")
    public void iHaveAProductWithSku(final String sku) {
        productDTO = newProduct(sku);
    }

    @When("I send a request to insert the product")
    public void iSendARequestToInsertTheProduct() {
        httpStatus = productHttpClient.insertProduct(productDTO);
    }

    @Then("the product is successfully inserted")
    public void theProductIsSuccessfullyInserted() {
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @When("I send a request to retrieve the product by SKU {string}")
    public void iSendARequestToRetrieveTheProductBySku(final String sku) {
        productDTO = productHttpClient.getProduct(sku);
    }

    @Then("I receive the product's data")
    public void iReceiveTheProductsData() {
        assertNotNull(productDTO);
        productHttpClient.deleteProduct("12345");
    }

    @When("I send a request to update the product")
    public void iSendARequestToUpdateTheProduct() {
        httpStatus = productHttpClient.updateProduct("12345", productDTO);
    }

    @Then("the product is successfully updated")
    public void theProductIsSuccessfullyUpdated() {
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @When("I send a request to delete the product by SKU {string}")
    public void iSendARequestToDeleteTheProductBySku(final String sku) {
        httpStatus = productHttpClient.deleteProduct(sku);
    }

    @Then("the product is successfully deleted")
    public void theProductIsSuccessfullyDeleted() {
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @Given("I have products in the category {string}")
    public void iHaveProductsInTheCategory(final String category) {
        productDTO = newProduct("12345");
        productDTO.setCategory(CategoryEnum.fromNome(category));
        productHttpClient.insertProduct(productDTO);
    }

    @When("I send a request to list products by category {string}")
    public void iSendARequestToListProductsByCategory(final String category) {
        ProductDTO[] products = productHttpClient.getProductsByCategory(category);
        assertNotNull(products);
        assertEquals(products.length > 0, true);
    }

    @Then("I receive a list of products in the category {string}")
    public void iReceiveAListOfProductsInTheCategory(final String category) {
        ProductDTO[] products = productHttpClient.getProductsByCategory(category);
        assertNotNull(products);
        assertEquals(products.length > 0, true);
    }

    private ProductDTO newProduct(String sku) {
        return new ProductDTO(
                sku,
                "Product 1",
                10.99,
                "Description for Product 1",
                "https://example.com/images/product1.jpg",
                CategoryEnum.LANCHE);
    }
}
