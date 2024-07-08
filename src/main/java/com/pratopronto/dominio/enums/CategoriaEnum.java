package com.pratopronto.dominio.enums;


import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;

public enum CategoriaEnum {
    LANCHE("lanche"),
    ACOMPANHAMENTO("acompanhamento"),
    BEBIDA("bebida"),
    SOBREMESA("sobremesa");

    CategoriaEnum(String nome) {
        this.nome = nome;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public static CategoriaEnum fromNome(String nome) throws NotFoundException {
        for(CategoriaEnum categoriaEnum : CategoriaEnum.values()){
            if(categoriaEnum.getNome().equals(nome)){
                return categoriaEnum;
            }
        }
        throw new NotFoundException("Categoria inexistente");
    }
}
