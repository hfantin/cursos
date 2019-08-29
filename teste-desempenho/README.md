# testes de desempenho

Com as variaveis abaixo:     

```
With -XX:+UseSerialGC This will perform garbage collection inline with the thread allocating the heap memory instead of a dedicated GC thread(s)
With -Xss512k This will limit each threads stack memory to 512KB instead of the default 1MB
With -XX:MaxRAM=72m This will restrict the JVM's calculations for the heap and non heap managed memory to be within the limits of this value.
```
> java -jar -XX:+UseSerialGC -XX:MaxRAM=72m -Xmx32m -Xss256k arquivo.jar
 
Links:   
- [medium](https://medium.com/@jean_sossmeier/spring-boot-jvm-1eea422be930)
- [script do fabric 8](https://github.com/fabric8io-images/run-java-sh/blob/master/fish-pepper/run-java-sh/fp-files/run-java.sh)
 
 
Consumo:   
 
- usando jetty: 262mb   
- usando tomcat com 1 thread: 281,3mb   
- usando tomcat com 50 threads(default): 290 mb   
- usando tomcat com 50 threads(default) e parametros -XX:+UseSerialGC -XX:MaxRAM=72m -Xmx32m -Xss256k:   250mb   s
- usando tomcat com spring lazy initialization: 226 - 271   
- rodando no docker com openjdk8 alpine: 520mb   
- rodando no docker com openjdk8 alpine e enviando parametros de jvm: 188 - 203   
- rodando no docker com graalvm: 447 mb   
- rodando no docker com script fabric 8: TODO  
- rodando no docker com openjdk8-openj9:alpine-slim: **~80mb**     