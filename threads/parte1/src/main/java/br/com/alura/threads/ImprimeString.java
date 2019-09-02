package br.com.alura.threads;

public class ImprimeString {

    public static void main(String[] args) {
        new ImprimeString().init();
    }

    private void init() {
        System.out.println("Imprime String");
        new Thread(() -> {
            System.out.println("Iniciando nova thread...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("fim");
        }).start();

    }
}
