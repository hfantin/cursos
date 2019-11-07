package br.com.caleum.cadastro.constantes;

/**
 * Created by Hamilton on 08/09/16.
 */
public enum TabelaAlunos {

    NOME_TABELA("ALUNOS"),
    COLUNA_ID("ID"),
    COLUNA_CAMINHO_FOTO("CAMINHO_FOTO"),
    COLUNA_NOME("NOME"),
    COLUNA_TELEFONE("TELEFONE"),
    COLUNA_ENDERECO("ENDERECO"),
    COLUNA_SITE("SITE"),
    COLUNA_NOTA("NOTA");

    private String descricao;
    TabelaAlunos(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
