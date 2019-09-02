package br.com.alura.threads.banheiro;

public enum Acao {
    MIJAR("mijando"), CAGAR("cagando"), TOMAR_BANHO("tomando banho"), FUMAR("fumando");

    private final String nome;

    Acao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
