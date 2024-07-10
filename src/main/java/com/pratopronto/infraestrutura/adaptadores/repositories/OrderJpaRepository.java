package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.Customer;
import com.pratopronto.infraestrutura.adaptadores.entidades.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    Optional<OrderEntity> findByCustomer(Customer customer);

    @Query("SELECT p FROM OrderEntity p WHERE p.status <> 'FINALIZADO' ORDER BY " +
            "CASE WHEN p.status = 'PRONTO' THEN 1 " +
            "WHEN p.status = 'EM_PREPARACAO' THEN 2 " +
            "WHEN p.status = 'RECEBIDO' THEN 3 ELSE 4 END, p.createDateTime ASC")
    List<OrderEntity> findOrdersByStatusAndCreateDateTime();
}
