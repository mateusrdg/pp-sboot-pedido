package com.pratopronto.dominio.enums;


import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;

public enum StatusEnum {

    RECEBIDO("recebido"),
    EM_PREPARACAO("em_preparacao"),
    PRONTO("pronto"),
    FINALIZADO("finalizado");

    StatusEnum(String nome) {
        this.nome = nome;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public static StatusEnum fromNome(String nome) throws NotFoundException {
        for(StatusEnum categoriaEnum : StatusEnum.values()){
            if(categoriaEnum.getNome().equals(nome)){
                return categoriaEnum;
            }
        }
        throw new NotFoundException("Status inexistente");
    }
}
