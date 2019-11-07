package com.androidteste.model;

/**
 * Created by hamilton on 10/10/17.
 */

public class ProgressModel {

    private int primario;
    private int secundario;
    private int valor;

    public ProgressModel(int primario, int secundario, int valor) {
        this.primario = primario;
        this.secundario = secundario;
        this.valor = valor;
    }

    public int getPrimario() {
        return primario;
    }

    public void setPrimario(int primario) {
        this.primario = primario;
    }

    public int getSecundario() {
        return secundario;
    }

    public void setSecundario(int secundario) {
        this.secundario = secundario;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
