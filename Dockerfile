FROM openjdk:17
EXPOSE 8080
ADD target/process.jar process.jar 
ENTRYPOINT ["java","-jar","/process.jar"]