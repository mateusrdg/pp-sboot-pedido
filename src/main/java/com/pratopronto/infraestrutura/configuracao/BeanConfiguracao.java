package com.pratopronto.infraestrutura.configuracao;

import com.pratopronto.dominio.adaptadores.services.CustomerServiceImp;
import com.pratopronto.dominio.adaptadores.services.OrderServiceImp;
import com.pratopronto.dominio.adaptadores.services.ProductServiceImp;
import com.pratopronto.dominio.portas.interfaces.CustomerServicePort;
import com.pratopronto.dominio.portas.interfaces.OrderServicePort;
import com.pratopronto.dominio.portas.interfaces.ProductServicePort;
import com.pratopronto.dominio.portas.repositories.CustomerRepositoryPort;
import com.pratopronto.dominio.portas.repositories.OrderRepositoryPort;
import com.pratopronto.dominio.portas.repositories.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {

    @Bean
    ProductServicePort productServicePort(ProductRepositoryPort productRepositoryPort) {
        return new ProductServiceImp(productRepositoryPort);
    }

    @Bean
    CustomerServicePort customerServicePort(CustomerRepositoryPort customerRepositoryPort) {
        return new CustomerServiceImp(customerRepositoryPort);
    }

    @Bean
    OrderServicePort orderServicePort(OrderRepositoryPort orderRepositoryPort, ProductRepositoryPort productRepositoryPort, CustomerRepositoryPort customerRepositoryPort) {
        return new OrderServiceImp(orderRepositoryPort, productRepositoryPort, customerRepositoryPort);
    }
}
