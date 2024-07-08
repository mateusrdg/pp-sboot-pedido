package com.pratopronto.dominio.adaptadores.services;

import com.pratopronto.dominio.Cliente;
import com.pratopronto.dominio.dtos.cliente.ClienteDTO;
import com.pratopronto.dominio.portas.repositories.ClienteRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ClienteServiceImpTest {

    @InjectMocks
    ClienteServiceImp clienteServiceImp;

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;
    @Test
    void testeConsultaPorId() {
        String cpf = "cpf";
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        when(clienteRepositoryPort.buscarPeloCpf(cpf)).thenReturn(Optional.of(cliente));

        ClienteDTO result = clienteServiceImp.buscarClientePorCpf(cpf);

        assertEquals(cpf, result.getCpf());
    }

}