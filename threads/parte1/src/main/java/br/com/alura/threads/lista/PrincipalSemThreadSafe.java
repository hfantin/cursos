package br.com.alura.threads.lista;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PrincipalSemThreadSafe {

    public static void main(String[] args) throws InterruptedException {
        List<String> lista = new ArrayList<>();
//        IntStream.range(0, 10).forEach(finalI -> new Thread(() -> IntStream.range(0, 100).mapToObj(j -> "Thread numero " + finalI + " numero " + j).forEach(lista::add)).start());

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                for(int j=0; j<100; j++) lista.add("thread " + finalI + " - " +j);
            }).start();
        }

        Thread.sleep(2000);

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " - " + lista.get(i)); //utilizando get(i)
        }
    }
}
