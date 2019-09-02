package br.com.alura.threads.banheiro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BanheiroSyncLock {

    private Lock lock = new ReentrantLock();

    public void mijar() {
        usarBanheiro(Acao.MIJAR, 2000);
    }

    public void cagar() {
        usarBanheiro(Acao.CAGAR, 5000);
    }

    public void banho() {
        usarBanheiro(Acao.TOMAR_BANHO, 10000);
    }

    public void fumar() {
        usarBanheiro(Acao.FUMAR, 3000);
    }

    private void usarBanheiro(Acao acao, int milis) {
        String nome = getNome();
        System.out.println("> " + nome + " está batendo na porta");
        lock.lock();
        System.out.println("\n- " + nome + " entrou no banheiro");
        System.out.println("- " + nome + " esta " + acao.getNome());
        sleep(milis);
        if (acao == Acao.CAGAR || acao == Acao.MIJAR) {
            System.out.println("- " + nome + " deu descarga");
            System.out.println("- " + nome + " lavou as mãos");
        }
        System.out.println("- " + nome + " saiu");
        lock.unlock();

    }

    private void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getNome() {
        return Thread.currentThread().getName();
    }

}
