# Testes de desempenho do springboot

Aplicativo de testes com 1 [endpoint](http://localhost:9000/v1/topicos) utilizando os módulos do springboot abaixo:   
- actuator   
- data-jpa     
- web   
- cache     

Consumo:   

| configuracao  | consumo  |
|---|---|
|  jetty  |  262mb |
|  tomcat - 5 threads |  281,3mb |
|  tomcat default(50 threads) |  290 mb |
|  tomcat default(50 threads) e parametros -XX:+UseSerialGC -XX:MaxRAM=72m -Xmx32m -Xss256k |  250mb  |
|  tomcat com spring lazy initialization |  226 - 271 |
|  docker com openjdk8 alpine |  520mb |
|  docker com openjdk8 alpine e enviando parametros de jvm  |  188 - 203 |
|  docker com graalvm  |  447 mb |
|  docker com script fabric 8 | TODO  |
|  docker com openjdk8-openj9:alpine-slim|  80mb |


Configurações da JVM:        

```
-XX:+UseSerialGC This will perform garbage collection inline with the thread allocating the heap memory instead of a dedicated GC thread(s)   
-Xss512k This will limit each threads stack memory to 512KB instead of the default 1MB   
-XX:MaxRAM=72m This will restrict the JVM's calculations for the heap and non heap managed memory to be within the limits of this value.
-Xshareclassesv - docker image open9
-Xquickstart    - docker image open9
ex.: java -jar -XX:+UseSerialGC -XX:MaxRAM=72m -Xmx32m -Xss256k arquivo.jar   
```


Links:   
- [medium](https://medium.com/@jean_sossmeier/spring-boot-jvm-1eea422be930)
- [script do fabric 8](https://github.com/fabric8io-images/run-java-sh/blob/master/fish-pepper/run-java-sh/fp-files/run-java.sh)
