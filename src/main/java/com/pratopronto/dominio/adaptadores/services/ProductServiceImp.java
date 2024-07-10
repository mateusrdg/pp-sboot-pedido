package com.pratopronto.dominio.adaptadores.services;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.BadRequestException;
import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.Product;
import com.pratopronto.dominio.dtos.product.ProductDTO;
import com.pratopronto.dominio.enums.CategoryEnum;
import com.pratopronto.dominio.portas.interfaces.ProductServicePort;
import com.pratopronto.dominio.portas.repositories.ProductRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImp implements ProductServicePort {

    private final ProductRepositoryPort productRepository;

    public ProductServiceImp(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void insert(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        validateInsert(productDTO);
        this.productRepository.save(product);
    }

    private void validateInsert(ProductDTO productDTO) {
        Optional<Product> queriedProduct  = this.productRepository.findBySku(productDTO.getSku());
        if (queriedProduct.isPresent())
            throw new BadRequestException("Sku ja cadastrado");
    }

    @Override
    public List<ProductDTO> findProducts() {
        List<Product> products = this.productRepository.findAll();
        return products.stream().map(Product::toProductDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductsBySku(List<String> skus) {
        return this.productRepository.findProductsBySku(skus).stream().map(Product::toProductDTO).collect(Collectors.toList());
    }

    @Override
    public void update(String sku, ProductDTO productDTO) throws NotFoundException {
        Optional<Product> produtoOptional = this.productRepository.findBySku(sku);

        if (produtoOptional.isEmpty())
            throw new NotFoundException("Produto não encontrado");

        Product product = produtoOptional.get();
        product.atualizar(productDTO);
        this.productRepository.save(product);
    }

    @Override
    public void delete(String sku) throws NotFoundException {
        Optional<Product> productOptional = this.productRepository.findBySku(sku);

        if (productOptional.isEmpty())
            throw new NotFoundException("Produto não encontrado");

        this.productRepository.delete(sku);
    }
    @Override
    public List<ProductDTO> findProductsByCategory(CategoryEnum categoryEnum){
        List<Product> products = this.productRepository.findByCategory(categoryEnum);
        return products.stream().map(Product::toProductDTO).collect(Collectors.toList());
    }
}
