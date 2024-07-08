package com.pratopronto.dominio.portas.repositories;

import com.pratopronto.dominio.Produto;
import com.pratopronto.dominio.enums.CategoriaEnum;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryPort {
    List<Produto> buscarTodos();

    List<Produto> buscarProdutosPorSku(List<String> skus);

    Optional<Produto> buscarPeloSku(String sku);

    List<Produto> buscarPorCategoria(CategoriaEnum categoria);

    void salvar(Produto produto);

    void deletar(String sku);
}
