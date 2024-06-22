FROM openjdk:17-jdk as builder
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17-jre
COPY --from=builder dependencies/ ./
RUN true
COPY --from=builder snapshot-dependencies/ ./
RUN true
COPY --from=builder spring-boot-loader/ ./
RUN true
COPY --from=builder application/ ./
RUN true
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

