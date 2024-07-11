package com.pratopronto.cucumber.config;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/ordercommons",
        plugin = {"pretty", "html:target/cucumber/ordercommons"},
        extraGlue = "com.pratopronto.ordercommons")
public class OrderCucumberIntegrationTest {
}
