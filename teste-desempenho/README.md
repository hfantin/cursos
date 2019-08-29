With -XX:+UseSerialGC This will perform garbage collection inline with the thread allocating the heap memory instead of a dedicated GC thread(s)

With -Xss512k This will limit each threads stack memory to 512KB instead of the default 1MB

With -XX:MaxRAM=72m This will restrict the JVM's calculations for the heap and non heap managed memory to be within the limits of this value.

 java -jar -XX:+UseSerialGC -XX:MaxRAM=72m -Xmx32m -Xss256k arquivo.jar
 
 
 https://medium.com/@jean_sossmeier/spring-boot-jvm-1eea422be930
 https://github.com/fabric8io-images/run-java-sh/blob/master/fish-pepper/run-java-sh/fp-files/run-java.sh
 
 
 consumo:
 
jetty 262mb
tomcat 1 thread 281,3mb
tomcat 50 threads 290 mb
tomcat 50  -XX:+UseSerialGC -XX:MaxRAM=72m -Xmx32m -Xss256k  250mb
lazy 226 - 271
docker - 520mb
docker com parametros: 188 - 203
docker com graalvm - 447 mb
docker com script do fabric 8 - 