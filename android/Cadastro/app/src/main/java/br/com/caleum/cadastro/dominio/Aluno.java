package br.com.caleum.cadastro.dominio;

import java.io.Serializable;

/**
 * Created by Hamilton on 08/09/16.
 */
public class Aluno implements Serializable{


    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private Double nota;
    private String caminhoFoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    @Override
    public String toString() {
//        return "Aluno{" +
//                "id=" + id +
//                ", nome='" + nome + '\'' +
//                ", telefone='" + telefone + '\'' +
//                ", endereco='" + endereco + '\'' +
//                ", site='" + site + '\'' +
//                ", nota=" + nota +
//                '}';
        return nome;
    }
}
