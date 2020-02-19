FROM openjdk:8-alpine
WORKDIR usr/src
ENV MONGO_DATABASE =  cricApp
ENV MONGO_URL = "mongodb://localhost:27017/cricApp"
ADD ./target/favouriteservice-*.jar /usr/src/favouriteservice-1.0.jar
EXPOSE 9300
ENTRYPOINT ["java", "-jar", "favouriteservice-1.0.jar"]