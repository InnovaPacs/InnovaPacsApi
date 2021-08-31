FROM openjdk:12
VOLUME /tmp
ADD ./target/innova.pacs-0.0.1-SNAPSHOT.jar innova.pacs.jar
ENTRYPOINT ["java", "-jar" , "/innova.pacs.jar"]