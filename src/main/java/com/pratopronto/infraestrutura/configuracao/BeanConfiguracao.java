package com.pratopronto.infraestrutura.configuracao;

import com.pratopronto.dominio.adaptadores.services.ClienteServiceImp;
import com.pratopronto.dominio.adaptadores.services.PedidoServiceImp;
import com.pratopronto.dominio.adaptadores.services.ProdutoServiceImp;
import com.pratopronto.dominio.portas.interfaces.ClienteServicePort;
import com.pratopronto.dominio.portas.interfaces.PedidoServicePort;
import com.pratopronto.dominio.portas.interfaces.ProdutoServicePort;
import com.pratopronto.dominio.portas.repositories.ClienteRepositoryPort;
import com.pratopronto.dominio.portas.repositories.PedidoRepositoryPort;
import com.pratopronto.dominio.portas.repositories.ProdutoRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {

    @Bean
    ProdutoServicePort produtoService(ProdutoRepositoryPort produtoRepositoryPort) {
        return new ProdutoServiceImp(produtoRepositoryPort);
    }

    @Bean
    ClienteServicePort clienteServicePort(ClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteServiceImp(clienteRepositoryPort);
    }

    @Bean
    PedidoServicePort pedidoServicePort(PedidoRepositoryPort pedidoRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort, ClienteRepositoryPort clienteRepositoryPort) {
        return new PedidoServiceImp(pedidoRepositoryPort, produtoRepositoryPort, clienteRepositoryPort);
    }
}
