Tipos de pool de trhead

A newFixedThreadPool é o pool de threads em que definimos previamente a quantidade de threads que queremos utilizar.
Assim, se por exemplo estabelecermos que queremos no máximo 4 threads, 
este número nunca será extrapolado e elas serão reaproveitadas.   

A newCachedThreadPool é o pool de threads que cresce dinamicamente de acordo com as solicitações.
É ideal quando não sabemos o número exato de quantas threads vamos precisar.
O legal deste pool é que ele também diminuí a quantidade de threads disponíveis quando uma thread 
fica ociosa por mais de 60 segundos.   

A newSingleThreadExecutor é o pool de threads que só permite uma única thread.   


https://www.caelum.com.br/apostila-java-orientacao-objetos/apendice-sockets/


# volatile
- acessar o mesmo atributo em várias threads pode criar problemas inesperados, pois a jvm cria uma copia dessa atributo na thread executada


- Threads possuem um cache.   
  - Esse cache faz com que nem sempre todas as variáveis serão vistas e atualizadas de maneira atômica.
- A palavra chave volatile evita o uso desse cache e faz que as threads sempre acessem a memória principal.
- Como alternativa, podemos utilizar as classes do pacote java.util.concurrent.atomic   
  - Vimos a classe AtomicBoolean como alternativa ao uso do volatile 
 
 https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/atomic/package-summary.html
 https://docs.oracle.com/javase/tutorial/essential/concurrency/atomicvars.html
 
 
 # tratamentos de erros nas threads
 
- Cada thread possui a sua pilha de métodos.
- O tratamento de exceções deve ser específico para cada pilha.
- Podemos plugar um UncaughtExceptionHandler para centralizar o tratamento.
- O pool de threads oferece uma fábrica de threads para personalizar a criação da thread.


- Fabrica de Threads:
```
public class FabricaDeThread implements ThreadFactory {

    private ThreadFactory defaultFactory;

    public FabricaDeThread(ThreadFactory defaultFactory) {
        this.defaultFactory = defaultFactory;
    }

    @Override
    public Thread newThread(Runnable tarefa) {

        //criando uma thread usando para fabrica padrão
        Thread thread = defaultFactory.newThread(tarefa); 

        //personalizando a thread
        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
        return thread;
    }
}

```
- para criar a fabrica de thread: 
```
ThreadFactory defaultFactory = Executors.defaultThreadFactory();
this.executor = Executors.newFixedThreadPool(5,new FabricaDeThread(defaultFactory));

```