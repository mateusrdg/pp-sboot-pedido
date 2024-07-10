package com.pratopronto.bagbasics;

import com.pratopronto.customercommons.CustomerHttpClient;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BagCucumberStepDefinitions {

    private final Logger log = LoggerFactory.getLogger(BagCucumberStepDefinitions.class);

    @Autowired
    private CustomerHttpClient customerHttpClient;

    @When("^I put (\\d+) (\\w+) in the bag$")
    public void i_put_something_in_the_bag(final int quantity, final String something) {
        IntStream.range(0, quantity)
                .peek(n -> log.info("Putting a {} in the bag, number {}", something, quantity))
                .map(ignore -> customerHttpClient.put(something))
                .forEach(statusCode -> assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value()));
    }


}
