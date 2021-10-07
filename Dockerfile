FROM harbor.geo.pks.t-mobile.com/duck/openjdk:11.3
ENV COMPONENT_NAME names_app
ENV PORT 8080
WORKDIR /opt/$COMPONENT_NAME
ADD docker-files/ .
ADD build/libs/*.jar tmobile-${COMPONENT_NAME}.jar

RUN chmod +x docker-entrypoint.sh
EXPOSE ${PORT}
ENTRYPOINT [ "./docker-entrypoint.sh" ]
