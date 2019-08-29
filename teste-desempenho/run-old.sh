#!/bin/sh
java -XX:+UseSerialGC -XX:MaxRAM=72m -Xmx32m -Xss256k -Xshareclasses -Xquickstart  \
     -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Dspring.profiles.active=$PROFILE -jar app.jar