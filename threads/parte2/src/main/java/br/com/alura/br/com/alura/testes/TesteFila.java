package br.com.alura.br.com.alura.testes;

import java.util.LinkedList;
import java.util.Queue;

public class TesteFila {

    public static void main(String[] args) {

//        Há outros métodos, como o peek(), para receber o primeiro elemento mas não remover, ou element(), que funciona igual ao peek(), mas joga uma exceção se não tiver nenhum elemento.
        Queue<String> fila = new LinkedList<>();
        fila.offer("c1");
        fila.offer("c2");
        fila.offer("c3");
        System.out.println(fila.poll());
        System.out.println(fila.poll());
        System.out.println(fila.poll());
        System.out.println(fila.poll());
        System.out.println(fila.size());
    }
}
