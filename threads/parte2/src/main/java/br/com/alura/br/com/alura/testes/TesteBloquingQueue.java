package br.com.alura.br.com.alura.testes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TesteBloquingQueue {
// esse é thread-safe
    public static void main(String[] args) throws InterruptedException {

//        Há outros métodos, como o peek(), para receber o primeiro elemento mas não remover, ou element(), que funciona igual ao peek(), mas joga uma exceção se não tiver nenhum elemento.
        BlockingQueue<String> fila = new ArrayBlockingQueue<>(3);
//        fila.offer("c1");
        //trava o MAIN se não houver mais elementos disponiveis
        fila.put("c1");
        fila.put("c2");
        fila.put("c3");
        fila.put("c4"); // trava aqui
//
//        System.out.println(fila.poll());
//        System.out.println(fila.poll());
//        System.out.println(fila.poll());
//        System.out.println(fila.poll());
        // trava se não houver mais elementos\\\
        System.out.println(fila.take());
        System.out.println(fila.take());
        System.out.println(fila.take());
        System.out.println(fila.take());
        System.out.println(fila.size());
    }
}
