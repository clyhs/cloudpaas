FROM tomcat:8.5

MAINTAINER 大鱼

VOLUME /tmp
ADD cloudpaas-demmo-ui-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/demoui.war

EXPOSE 8080

ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh","run"]