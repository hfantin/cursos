package br.com.alura.threads.banheiro;

public class PrincipalWaitNotify {

    public static void main(String[] args) {
        System.out.println("Principal2.main");

        /*
        outra forma de fazer o lock definindo um timeout

        boolean locked = lock.tryLock(5, TimeUnit.SECONDS); //5s
        Com esse método esperamos até cinco segundos e receberemos true caso o lock for obtido.
        Caso contrário, receberemos false.
        Como desvantagem, há o fato de o programador ter a responsabilidade de liberar o lock (unlock()).
         */
        BanheiroWaitNotify b = new BanheiroWaitNotify();

        Thread limpar = new Thread(b::limpar, "Limpeza");
        limpar.setDaemon(true); // finaliza a thread quando não for mais necessario - essa thread é um provedor de servico
        limpar.start();
        new Thread(b::cagar, "Chaves").start();
//        new Thread(b::banho, "Madruga").start();
//        new Thread(b::cagar, "Kiko").start();
        new Thread(b::mijar, "Chiquinha").start();
//        new Thread(b::cagar, "Florinda").start();
//        new Thread(b::fumar, "Girafales").start();
//        new Thread(b::mijar, "Clotilde").start();
    }
}
