FROM openjdk:20-jdk
COPY target/trips-1.0-SNAPSHOT.jar trips-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/trips-1.0-SNAPSHOT.jar"]