package com.pratopronto.dominio.portas.interfaces;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.cliente.ClienteDTO;

public interface ClienteServicePort {

    ClienteDTO buscarClientePorCpf(String cpf)throws NotFoundException;

    void cadastraCliente(ClienteDTO clienteDTO);

}
