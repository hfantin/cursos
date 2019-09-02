package br.com.alura.threads.buscatextual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BuscaTextual {

    public void buscar(String nomeArquivo, String nome) {
        try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
            int numeroLinha = 1;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
//                if (linha.toLowerCase().contains(nome.toLowerCase())) {
                if (linha.matches(nome)) {
                    System.out.println(nomeArquivo + " - " + numeroLinha + " - " + linha);
                }
                numeroLinha++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
