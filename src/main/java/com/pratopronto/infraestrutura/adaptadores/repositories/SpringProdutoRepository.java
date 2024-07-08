package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.enums.CategoriaEnum;
import com.pratopronto.infraestrutura.adaptadores.entidades.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    Optional<ProdutoEntity> findBySku(String sku);

    void deleteBySku(String sku);

    List<ProdutoEntity> findAllByCategoria(CategoriaEnum categoria);

    List<ProdutoEntity> findAllBySkuIn(List<String> skus);
}
