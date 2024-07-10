package com.pratopronto.dominio.enums;


import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;

public enum CategoryEnum {
    LANCHE("lanche"),
    ACOMPANHAMENTO("acompanhamento"),
    BEBIDA("bebida"),
    SOBREMESA("sobremesa");

    CategoryEnum(String nome) {
        this.nome = nome;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public static CategoryEnum fromNome(String nome) throws NotFoundException {
        for(CategoryEnum categoryEnum : CategoryEnum.values()){
            if(categoryEnum.getNome().equals(nome)){
                return categoryEnum;
            }
        }
        throw new NotFoundException("Categoria inexistente");
    }
}
