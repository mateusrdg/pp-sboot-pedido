package com.pratopronto.infraestrutura.adaptadores.repositories;

import com.pratopronto.dominio.Produto;
import com.pratopronto.dominio.enums.CategoriaEnum;
import com.pratopronto.dominio.portas.repositories.ProdutoRepositoryPort;
import com.pratopronto.infraestrutura.adaptadores.entidades.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProdutoRepository implements ProdutoRepositoryPort {

    private final SpringProdutoRepository springProdutoRepository;

    public ProdutoRepository(SpringProdutoRepository springProdutoRepository) {
        this.springProdutoRepository = springProdutoRepository;
    }

    @Override
    public List<Produto> buscarTodos() {
        List<ProdutoEntity> produtoEntities = this.springProdutoRepository.findAll();
        return produtoEntities.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public List<Produto> buscarProdutosPorSku(List<String> skus) {
        List<ProdutoEntity> produtoEntities =  this.springProdutoRepository.findAllBySkuIn(skus);
        return produtoEntities.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> buscarPeloSku(String sku) {
        return this.springProdutoRepository.findBySku(sku).map(ProdutoEntity::toProduto);
    }

    @Override
    public List<Produto> buscarPorCategoria(CategoriaEnum categoria) {
        List<ProdutoEntity> produtos = this.springProdutoRepository.findAllByCategoria(categoria);
        return produtos.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public void salvar(Produto produto) {
        ProdutoEntity produtoEntity;
        if (Objects.isNull(produto.getId()))
            produtoEntity = new ProdutoEntity(produto);
        else {
            produtoEntity = this.springProdutoRepository.findById(produto.getId()).get();
            produtoEntity.atualizar(produto);
        }

        this.springProdutoRepository.save(produtoEntity);
    }

    @Override
    public void deletar(String sku) {
        this.springProdutoRepository.deleteBySku(sku);
    }
}
