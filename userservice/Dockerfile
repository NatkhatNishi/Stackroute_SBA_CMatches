FROM openjdk:8-alpine
WORKDIR usr/src
ENV MYSQL_HOST: localhost
ENV MYSQL_DATABASE: cricapp
ENV MYSQL_USER: root
ENV MYSQL_PASSWORD: admin
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
EXPOSE 9100
ENTRYPOINT ["java", "-jar", "/usr/src/userservice-1.0.jar"]