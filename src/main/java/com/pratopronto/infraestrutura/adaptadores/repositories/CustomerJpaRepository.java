package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.infraestrutura.adaptadores.entidades.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByCpf(String cpf);
}
