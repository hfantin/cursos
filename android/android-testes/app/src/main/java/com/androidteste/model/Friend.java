package com.androidteste.model;

/**
 * Created by hamilton on 21/09/17.
 */

public class Friend {

    private String nome;
    private String telefone;

    public Friend() {
    }

    public Friend(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
