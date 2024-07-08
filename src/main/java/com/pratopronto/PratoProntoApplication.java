package com.pratopronto;

import com.pratopronto.infraestrutura.adaptadores.repositories.SpringProdutoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = SpringProdutoRepository.class)
public class PratoProntoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PratoProntoApplication.class, args);
    }

}
