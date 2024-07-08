package com.pratopronto.dominio.portas.interfaces;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.produto.ProdutoDTO;
import com.pratopronto.dominio.enums.CategoriaEnum;

import java.util.List;

public interface ProdutoServicePort {

    List<ProdutoDTO> buscarProdutos();

    List<ProdutoDTO> buscarProdutosPorSkus(List<String> skus);

    void criar(ProdutoDTO produtoDTO);

    void atualizar(String sku, ProdutoDTO produtoDTO) throws NotFoundException;

    void deletar(String sku) throws NotFoundException;

    List<ProdutoDTO> buscarPorCategoria(CategoriaEnum categoria) throws NotFoundException;
}
