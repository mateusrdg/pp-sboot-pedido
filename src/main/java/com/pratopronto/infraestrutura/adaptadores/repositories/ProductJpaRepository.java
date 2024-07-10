package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.enums.CategoryEnum;
import com.pratopronto.infraestrutura.adaptadores.entidades.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findBySku(String sku);

    void deleteBySku(String sku);

    List<ProductEntity> findAllByCategory(CategoryEnum category);

    List<ProductEntity> findAllBySkuIn(List<String> skus);
}
