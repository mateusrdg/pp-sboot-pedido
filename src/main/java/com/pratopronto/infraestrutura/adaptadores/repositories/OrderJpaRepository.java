package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.infraestrutura.adaptadores.entidades.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByCustomerCpf(String cpf);

    @Query("SELECT p FROM OrderEntity p WHERE p.status <> 'FINALIZADO' ORDER BY " +
            "CASE WHEN p.status = 'PRONTO' THEN 1 " +
            "WHEN p.status = 'EM_PREPARACAO' THEN 2 " +
            "WHEN p.status = 'RECEBIDO' THEN 3 ELSE 4 END, p.createDateTime ASC")
    List<OrderEntity> findOrdersByStatusAndCreateDateTime();
}
