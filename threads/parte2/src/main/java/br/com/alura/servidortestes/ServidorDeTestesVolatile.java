package br.com.alura.servidortestes;

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
 * usando a palavra chave volatile resolve o problema da classe ServidorDeTestes (cache de variaveis na thread)
 *
 *
 */
public class ServidorDeTestesVolatile {

    private volatile boolean estaRodando = false;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTestesVolatile servidor = new ServidorDeTestesVolatile();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        new Thread(() -> {
            System.out.println("Servidor começando, estaRodando = " + estaRodando );

            while(!estaRodando) {}

            System.out.println("Servidor rodando, estaRodando = " + estaRodando );

            while(estaRodando) {}

            System.out.println("Servidor terminando, estaRodando = " + estaRodando );
        }).start();
    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        estaRodando = true;

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        estaRodando = false;
    }
}