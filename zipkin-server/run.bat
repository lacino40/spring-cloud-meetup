@echo off
SET "JAVA_HOME=c:\Program Files\Java\jdk-21.0.2\"
SET "PATH=%JAVA_HOME%\bin;%PATH%"

java -jar lib\zipkin-server-3.0.5-exec.jar --EUREKA_SERVICE_URL=http://localhost:8761/eureka/v2