FROM openjdk:17-jdk-slim

EXPOSE 3306
EXPOSE 4372
EXPOSE 6379
EXPOSE 8090

ARG UID
ARG USERNAME
ARG GID
ARG GROUP

ENV HOME=/home/$USERNAME
RUN mkdir -p $HOME && \
    addgroup --gid "$GID" "$USERNAME" && \
    adduser --uid "$UID" --disabled-password --gecos "" --ingroup "$USERNAME" --no-create-home  "$USERNAME" && \
    chown -R $USERNAME:$USERNAME $HOME

ENV MODEL_CACHE="/mnt/data/modelCache"
ENV APP_HOME=$HOME/fileservice
RUN mkdir $APP_HOME && \
    chown -R $USERNAME $APP_HOME

USER $USERNAME
ENV JAR=OmexService-1.0-SNAPSHOT.jar
COPY --chown=$USERNAME:$USERNAME target/$JAR $APP_HOME/$JAR

WORKDIR $APP_HOME
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "OmexService-1.0-SNAPSHOT.jar"]