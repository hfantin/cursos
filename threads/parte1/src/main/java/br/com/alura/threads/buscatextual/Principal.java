package br.com.alura.threads.buscatextual;

public class Principal {

    public static void main(String [] args) {

//        String regex = "da";
//            \\s - significa whitespace, um espaço ou tab
//            \\w - significa word, um caractere ou número
        String regex = "(Dan|Chad)(\\s|\\w)*";

        System.out.println("buscando regex " + regex);
        new Thread(() -> new BuscaTextual().buscar("assinaturas1.txt", regex)).start();
        new Thread(() -> new BuscaTextual().buscar("assinaturas2.txt", regex)).start();
        new Thread(() -> new BuscaTextual().buscar("autores.txt", regex)).start();
    }

}
