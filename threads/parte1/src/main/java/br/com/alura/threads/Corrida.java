package br.com.alura.threads;

public class Corrida {

    public static void main(String [] args) {


        new Thread(() -> new Corrida().executar(), "tosco1").start();
        new Thread(() -> new Corrida().executar(), "tosco2").start();
    }

    private void executar() {
        Thread currentThread = Thread.currentThread();
        for(int i=0; i<1000;i++){
            System.out.println(currentThread.getName() + " " + currentThread.getId()+ " - numero " + i);
        }
    }
}
