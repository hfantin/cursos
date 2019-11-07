package com.androidteste.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamilton on 10/12/16.
 */

public class Grupo {

    private Integer codigo;
    private String nome;
    private List<String> categorias = new ArrayList<>();

    public Grupo() {
    }

    public Grupo(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Grupo(Integer codigo, String nome, List<String> categorias) {
        this.codigo = codigo;
        this.nome = nome;
        this.categorias = categorias;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", categorias=" + categorias +
                '}';
    }
}
