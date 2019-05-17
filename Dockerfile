FROM java:8
VOLUME /tmp
ARG JAR_FILE=build/libs/notesaver-0.0.1-SNAPSHOT.jar
COPY build/libs/notesaver-0.0.1-SNAPSHOT.jar notesaver.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/notesaver.jar"]
EXPOSE 1235
