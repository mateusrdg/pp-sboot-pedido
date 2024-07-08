package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.Cliente;
import com.pratopronto.infraestrutura.adaptadores.entidades.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringPedidoRepository extends JpaRepository<PedidoEntity, UUID> {
    Optional<PedidoEntity> findByCliente(Cliente cliente);

    @Query("SELECT p FROM PedidoEntity p WHERE p.status <> 'FINALIZADO' ORDER BY " +
            "CASE WHEN p.status = 'PRONTO' THEN 1 " +
            "WHEN p.status = 'EM_PREPARACAO' THEN 2 " +
            "WHEN p.status = 'RECEBIDO' THEN 3 ELSE 4 END, p.dataCriacao ASC")
    List<PedidoEntity> findPedidosPorStatusEDataCriacao();
}
