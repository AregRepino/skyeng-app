FROM amazoncorretto:17.0.7
ADD build/libs/*.jar ./
EXPOSE 9009
ENTRYPOINT [ "java" ]
CMD [ "-jar", "skyeng-app-0.0.1-SNAPSHOT.jar" ]
