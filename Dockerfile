
FROM openjdk:11
ARG JAR_FILE=out/artifacts/homepage_jar
COPY ${JAR_FILE} homepage.jar
ENTRYPOINT ["java","-jar","/homepage.jar"]