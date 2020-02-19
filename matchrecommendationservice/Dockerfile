FROM openjdk:8-alpine
WORKDIR usr/src
ENV MONGO_DATABASE =  cricApp
ENV MONGO_URL = "mongodb://localhost:27017/cricApp"
ADD ./target/matchrecommendationservice-*.jar /usr/src/matchrecommendationservice-1.0.jar
EXPOSE 9200
ENTRYPOINT ["java", "-jar", "matchrecommendationservice-1.0.jar"]