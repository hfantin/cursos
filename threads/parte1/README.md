# Curso de threads do alura


## Links
- como não aprender herança
> blog.caelum.com.br/como-nao-aprender-orientacao-a-objetos-heranca/


- Thread Daemon   
São provedores de serviços para outras threads.   
Threads daemon são como prestadores de serviços para outras threads.   
Elas são usadas para dar apoio à tarefas e só são necessárias rodar quando as threads "normais"   
ainda estão sendo executadas. Uma thread daemon não impede a JVM de terminar desde que não existem   
 mais threads principais em execução. Um exemplo de uma thread daemon é o coletor de lixo da JVM (Garbage Collector) ou a nossa limpeza do banheiro :)   
Para definir uma thread como daemon basta usar o método setDaemon(boolean) antes de inicializar.   