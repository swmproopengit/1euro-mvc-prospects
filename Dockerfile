FROM openjdk:8
EXPOSE 8097
ADD target/mvc-prospects.jar mvc-prospects.jar
ENTRYPOINT ["java","-jar","/mvc-prospects"]