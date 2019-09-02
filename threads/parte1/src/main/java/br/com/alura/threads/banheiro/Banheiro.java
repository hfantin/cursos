package br.com.alura.threads.banheiro;

public class Banheiro {

    public void mijar() {
        usarBanheiro("mijando", 5000);
    }

    public void cagar()  {
       usarBanheiro("cagando", 10000);
    }

    private void usarBanheiro(String acao, int milis) {
        System.out.println(getNome() + " entrou no banheiro");
        System.out.println(getNome() + " esta " + acao);
        sleep(milis);
        System.out.println(getNome() + " deu descarga");
        System.out.println(getNome() + " saiu");

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
