package br.com.alura.servidor;

import br.com.alura.comandos.ComandoC1;
import br.com.alura.comandos.ComandoC2AcessaBanco;
import br.com.alura.comandos.ComandoC2ChamaWS;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefasV3 {


    private AtomicBoolean estaRodando;
    private final ServerSocket servidor;
    private final ExecutorService poolDeThreads;

    private static int numero = 1;

    public ServidorTarefasV3() throws IOException {
        System.out.println("--- Iniciando Servidor ---");
        this.servidor = new ServerSocket(12345);
        this.poolDeThreads = Executors.newCachedThreadPool();

//        this.poolDeThreads = Executors.newFixedThreadPool(4, r -> fabricaDeThreads(r));
        this.estaRodando = new AtomicBoolean(true);
    }

    public Thread fabricaDeThreads(Runnable tarefa) {
        ThreadFactory defaultFactory = Executors.defaultThreadFactory();
        Thread thread = defaultFactory.newThread(tarefa);
        thread.setName("Thread-Servidor-Tarefas-" + numero);
//        Thread thread = new Thread(r, "Thread-Servidor-Tarefas-" + numero);
        numero++;
        thread.setUncaughtExceptionHandler((t, e) ->
                System.out.println("Deu exceção na thread " + t.getName() + ", " + e.getMessage()));
        return thread;
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
//                informacoes();
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

    private void executar(Socket socket, ServidorTarefasV3 servidorTarefas) {
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
                        ComandoC1 c1 = new ComandoC1(saidaCliente);
                        this.poolDeThreads.execute(c1);
                        break;

                    case "c2":
                        saidaCliente.println("Confirmação do comando c2");

                        //criando os dois comandos
                        ComandoC2ChamaWS c2WS = new ComandoC2ChamaWS(saidaCliente);
                        ComandoC2AcessaBanco c2Banco = new ComandoC2AcessaBanco(saidaCliente);

                        //passando os comandos para o pool, resultado é um Future
                        Future<String> futureWS = this.poolDeThreads.submit(c2WS);
                        Future<String> futureBanco = this.poolDeThreads.submit(c2Banco);

                        //novo, passando a tarefa para juntar os resultados para o pool
                        this.poolDeThreads.submit(() -> juntarResultados(futureWS, futureBanco, saidaCliente));


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

    private Future<Void> juntarResultados(Future<String> futureWS, Future<String> futureBanco, PrintStream saidaCliente) {
        System.out.println("Aguardando resultados do future WS e Banco");

        try {
            String numeroMagico = futureWS.get(20, TimeUnit.SECONDS);
            String numeroMagico2 = futureBanco.get(20, TimeUnit.SECONDS);

            saidaCliente.println("Resultado do comando c2: "
                    + numeroMagico + ", " + numeroMagico2);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {

            System.out.println("Timeout: Cancelando a execução do comando c2");

            saidaCliente.println("Timeout na execução do comando c2");
            futureWS.cancel(true);
            futureBanco.cancel(true);
        }

        System.out.println("Finalizou JuntaResultadosFutureWSFutureBanco");

        return null; //esse Callable não tem retorno, por isso null
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
        ServidorTarefasV3 servidorTarefas = new ServidorTarefasV3();
        System.out.println("rodar servidor...");
        servidorTarefas.rodar();
        System.out.println("fim");
    }


}
