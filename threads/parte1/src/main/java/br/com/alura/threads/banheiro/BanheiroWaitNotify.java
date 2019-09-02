package br.com.alura.threads.banheiro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BanheiroWaitNotify {


    private boolean estaSujo = true;

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

        synchronized (this){
            System.out.println("\n- " + nome + " entrou no banheiro");
            while(this.estaSujo) {
                esperarLaFora(nome);
            }
            System.out.println("- " + nome + " esta " + acao.getNome());
            sleep(milis);
            if (acao == Acao.CAGAR || acao == Acao.MIJAR) {
                this.estaSujo = true;
                System.out.println("- " + nome + " deu descarga");
                System.out.println("- " + nome + " lavou as mãos");
            }
            System.out.println("- " + nome + " saiu");

        }


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

    private void esperarLaFora(String nome) {
        try {
            System.out.println("- " + nome + " sai do banheiro por que ele está sujo.");
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void limpar() {
        while (true){
            executarLimpeza();
            sleep(10000);
        }

    }

    private void executarLimpeza() {
        String nome = getNome();
        System.out.println("> " + nome + " está batendo na porta");

        synchronized (this){
            System.out.println("\n- " + nome + " entrou no banheiro");
            if(!this.estaSujo) {
                System.out.println("\n- " + nome + ", não está sujo, vou sair");
                return;
            }
            System.out.println("- " + nome + " esta limpando o banheiro");
            sleep(1000);
            this.estaSujo = false;
            this.notifyAll();
            System.out.println("- " + nome + " saiu");

        }
    }
}
