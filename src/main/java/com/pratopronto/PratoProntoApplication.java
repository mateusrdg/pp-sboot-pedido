package com.pratopronto;

import com.pratopronto.infraestrutura.adaptadores.repositories.ProductJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
@EnableJpaRepositories(basePackageClasses = ProductJpaRepository.class)
public class PratoProntoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PratoProntoApplication.class, args);
    }

}
