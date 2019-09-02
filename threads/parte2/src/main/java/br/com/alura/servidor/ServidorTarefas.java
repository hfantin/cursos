package br.com.alura.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {


    private AtomicBoolean estaRodando;
    private final ServerSocket servidor;
    private final ExecutorService poolDeThreads;

    public ServidorTarefas() throws IOException {
        System.out.println("--- Iniciando Servidor ---");
        this.servidor = new ServerSocket(12345);
        this.poolDeThreads = Executors.newCachedThreadPool();
        this.estaRodando = new AtomicBoolean(true);
    }

    // pool com somente uma thread
    // ->  ExecutorService poolDeThreads = Executors.newSingleThreadExecutor();
    // pool de threads com tamanho fixo
    // ->  ExecutorService poolDeThreads = Executors.newFixedThreadPool(5);
    // pool de threads dinamica
    // ->  new Thread(() -> executar(socket)).start();

    public void rodar() throws IOException {
        try {
            while (this.estaRodando.get()) {
                Socket socket = servidor.accept();
                poolDeThreads.execute(() -> executar(socket, this));
                informacoes();
            }
        } catch (SocketException e) {
            System.out.println("SocketException, está rodando? " + this.estaRodando);
        }
    }

    public void parar() throws IOException {
        System.out.println("Parando servidor");
        this.estaRodando.set(false);
        poolDeThreads.shutdown();
        servidor.close();
    }

    private void executar(Socket socket, ServidorTarefas servidorTarefas) {
        System.out.println("Aceitando novo cliente na porta " + socket.getPort());
        try {
            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (entradaCliente.hasNextLine()) {
                String comando = entradaCliente.nextLine();
                switch (comando) {
                    case "c1":
                        // confirmação do o cliente
                        saidaCliente.println("Confirmação do comando c1");
                        break;

                    case "c2":
                        saidaCliente.println("Confirmação do comando c2");
                        break;
                    case "fim":
                        saidaCliente.println("Desligando o servidor...");
                        servidorTarefas.parar();
                        break;

                    default:
                        saidaCliente.println("Comando não encontrado: " + comando);
                        break;

                }
                System.out.println("comando: " + comando);

            }
            saidaCliente.close();
            entradaCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void informacoes() {
        System.out.println("---------------------------------------------");
        Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();
        for (Thread thread : todasAsThreads) {
            System.out.println(thread.getName());
        }
        Runtime runtime = Runtime.getRuntime();
        int qtdProcessadores = runtime.availableProcessors();
        System.out.println("Qtd de processadores: " + qtdProcessadores);
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) throws IOException {
        ServidorTarefas servidorTarefas = new ServidorTarefas();
        System.out.println("rodar servidor...");
        servidorTarefas.rodar();
        System.out.println("fim");
    }


}
