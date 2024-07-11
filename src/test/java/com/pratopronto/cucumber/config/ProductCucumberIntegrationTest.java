package com.pratopronto.cucumber.config;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/productcommons",
        plugin = {"pretty", "html:target/cucumber/productcommons"},
        extraGlue = "com.pratopronto.productcommons")
public class ProductCucumberIntegrationTest {
}
