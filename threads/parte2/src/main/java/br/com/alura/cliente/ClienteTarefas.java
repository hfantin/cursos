package br.com.alura.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("conexao estabelecida");
        Thread threadEnvioComando = new Thread(() -> enviarComando(socket));
        Thread threadReceberComando = new Thread(() -> receberComando(socket));

        threadEnvioComando.start();
        threadReceberComando.start();

        //thread main vai esperar
        threadReceberComando.join();
        System.out.println("finalizando cliente...");

        socket.close();
    }

    private static void receberComando(Socket socket) {
        try {
            System.out.println("Recebendo dados do servidor");
            Scanner respostaServidor = new Scanner(socket.getInputStream());
            while (respostaServidor.hasNextLine()) {
                String linha = respostaServidor.nextLine();
                System.out.println("resposta: " + linha);
            }
            respostaServidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void enviarComando(Socket socket) {
        try {
            PrintStream saida = new PrintStream(socket.getOutputStream());
            // enviar comando
            System.out.println("Digite um comando para enviar ao servidor:");
            Scanner teclado = new Scanner(System.in);
            while (teclado.hasNextLine()) {
                String linha = teclado.nextLine();
                if (linha.trim().equals("")) {
                    System.out.println("saindo...");
                    break;
                }
                saida.println(linha);
            }
            saida.close();
            teclado.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
