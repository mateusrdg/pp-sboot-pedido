package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.Product;
import com.pratopronto.dominio.enums.CategoryEnum;
import com.pratopronto.dominio.portas.repositories.ProductRepositoryPort;
import com.pratopronto.infraestrutura.adaptadores.entidades.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepository implements ProductRepositoryPort {

    private final ProductJpaRepository productJpaRepository;

    public ProductRepository(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntities = this.productJpaRepository.findAll();
        return productEntities.stream().map(ProductEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsBySku(List<String> skus) {
        List<ProductEntity> productEntities =  this.productJpaRepository.findAllBySkuIn(skus);
        return productEntities.stream().map(ProductEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return this.productJpaRepository.findBySku(sku).map(ProductEntity::toProduto);
    }

    @Override
    public List<Product> findByCategory(CategoryEnum categoria) {
        List<ProductEntity> produtos = this.productJpaRepository.findAllByCategory(categoria);
        return produtos.stream().map(ProductEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public void save(Product product) {
        ProductEntity productEntity;
        if (Objects.isNull(product.getId()))
            productEntity = new ProductEntity(product);
        else {
            productEntity = this.productJpaRepository.findById(product.getId()).get();
            productEntity.atualizar(product);
        }

        this.productJpaRepository.save(productEntity);
    }

    @Override
    public void delete(String sku) {
        this.productJpaRepository.deleteBySku(sku);
    }
}
