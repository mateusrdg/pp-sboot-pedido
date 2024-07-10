package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.product.ProductDTO;
import com.pratopronto.dominio.enums.CategoryEnum;
import com.pratopronto.dominio.portas.interfaces.ProductServicePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductServicePort productServicePort;

    public ProductController(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @PostMapping
    void insert(@RequestBody ProductDTO productDTO) {
        productServicePort.insert(productDTO);
    }

    @GetMapping
    List<ProductDTO> getProducts() {
        return productServicePort.findProducts();
    }

    @PutMapping(value = "/{sku}")
    void update(@PathVariable String sku, @RequestBody ProductDTO productDTO) throws NotFoundException {
        productServicePort.update(sku, productDTO);
    }

    @DeleteMapping(value = "/{sku}")
    void delete(@PathVariable String sku) throws NotFoundException {
        productServicePort.delete(sku);
    }

    @GetMapping(value = "/categoria/{categoria}")
    List<ProductDTO> buscarPorCategoria(@PathVariable String categoria) throws NotFoundException {
        return productServicePort.findProductsByCategory(CategoryEnum.fromNome(categoria));
    }
}
