FROM java:8-jre
WORKDIR usr/src
ADD ./target/eureka-service-*.jar /usr/src/eureka-service-1.0.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/usr/src/eureka-service-1.0.jar"]