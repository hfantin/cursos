package br.com.alura.threads.lista;

import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

public class PrincipalThreadSafe {

    public static void main(String[] args) throws InterruptedException {
        List<String> lista = new Vector<>();
//        IntStream.range(0, 10).forEach(finalI -> new Thread(() -> IntStream.range(0, 100).mapToObj(j -> "Thread numero " + finalI + " numero " + j).forEach(lista::add)).start());

        IntStream.range(0, 10).forEach(finalI -> new Thread(() -> {
            IntStream.range(0, 100).mapToObj(j -> "thread " + finalI + " - " + j).forEach(lista::add);
        }).start());

        Thread.sleep(2000);

        //utilizando get(i)
        lista.forEach(System.out::println);
    }
}
