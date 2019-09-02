package br.com.alura.servidortestes;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * esse servidor serve apenas para testes e compartilha a variavel estaRodando entre threads
 *
 * comportamento esperado:
 *
 * Servidor começando, estaRodando = false
 * Main alterando estaRodando = true
 * Servidor rodando, estaRodando = true
 * Main alterando estaRodando = false
 * Servidor terminando, estaRodando = false
 *
 * usando variaveis atomicas também resolvemos o problema da classe ServidorDeTestes (cache de variaveis na thread)
 *
 *
 */
public class ServidorDeTestesAtomicComExcecao {

    private AtomicBoolean estaRodando = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTestesAtomicComExcecao servidor = new ServidorDeTestesAtomicComExcecao();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        Thread thread = new Thread(() -> {
            System.out.println("Servidor começando, estaRodando = " + estaRodando);

            // inclui erro aqui
            if (estaRodando.get()) {
                throw new RuntimeException("Deu erro na trhead...");
            }

            while (!estaRodando.get()) {
            }

            System.out.println("Servidor rodando, estaRodando = " + estaRodando);

            while (estaRodando.get()) {
            }

            System.out.println("Servidor terminando, estaRodando = " + estaRodando);
        });
        thread.setUncaughtExceptionHandler((t, e) ->
                System.out.println("Deu exceção na thread " + t.getName() + ", " + e.getMessage()));
        thread.start();
    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        estaRodando.set(true);

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        estaRodando.set(false);
    }
}