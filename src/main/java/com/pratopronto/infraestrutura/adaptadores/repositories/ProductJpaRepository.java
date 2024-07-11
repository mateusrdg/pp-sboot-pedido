package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.enums.CategoryEnum;
import com.pratopronto.infraestrutura.adaptadores.entidades.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findBySku(String sku);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductEntity p WHERE p.sku = :sku")
    void deleteBySku(@Param("sku") String sku);

    List<ProductEntity> findAllByCategory(CategoryEnum category);

    List<ProductEntity> findAllBySkuIn(List<String> skus);
}
