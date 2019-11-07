package com.github.hfantin.clima.java.domain.model;

import java.util.Date;

public class Temperatura {

    public Integer icone;
    public Date data;
    public String descricao;
    public Double temperaturaMaxima;
    public Double temperaturaMinima;

    public Temperatura() {
    }

    public Temperatura(Integer icone, Date data, String descricao, Double temperaturaMaxima, Double temperaturaMinima) {
        this.icone = icone;
        this.data = data;
        this.descricao = descricao;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
    }

    public Integer getIcone() {
        return icone;
    }

    public void setIcone(Integer icone) {
        this.icone = icone;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(Double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public Double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(Double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "icone=" + icone +
                ", data='" + data + '\'' +
                ", descricao='" + descricao + '\'' +
                ", temperaturaMaxima='" + temperaturaMaxima + '\'' +
                ", temperaturaMinima='" + temperaturaMinima + '\'' +
                '}';
    }
}
