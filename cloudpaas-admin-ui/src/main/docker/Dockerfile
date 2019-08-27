FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD cloudpaas-admin-ui-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-jar","/app.war"]