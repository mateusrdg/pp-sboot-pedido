package com.pratopronto.cucumber.config;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/customercommons",
        plugin = {"pretty", "html:target/cucumber/customercommons"},
        extraGlue = "com.pratopronto.customercommons")
public class CustomerCucumberIntegrationTest {
}
