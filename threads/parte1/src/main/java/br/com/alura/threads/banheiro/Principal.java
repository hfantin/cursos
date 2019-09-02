package br.com.alura.threads.banheiro;

public class Principal {

    public static void main(String[] args) {
        System.out.println("Principal.main");
//        Banheiro b = new Banheiro();
        BanheiroSync b = new BanheiroSync();

        new Thread(b::cagar, "Chaves").start();
        new Thread(b::banho, "Madruga").start();
        new Thread(b::cagar, "Kiko").start();
        new Thread(b::mijar, "Chiquinha").start();
        new Thread(b::cagar, "Florinda").start();
        new Thread(b::fumar, "Girafales").start();
        new Thread(b::mijar, "Clotilde").start();
    }
}
