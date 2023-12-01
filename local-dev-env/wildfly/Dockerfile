FROM quay.io/wildfly/wildfly:26.1.3.Final-jdk11

WORKDIR /

USER root
RUN mkdir -p resources

USER jboss
COPY ./postgresql.jar /resources
COPY ./datasource.cli /resources
COPY ./logs.cli /resources

RUN /opt/jboss/wildfly/bin/add-user.sh -u admin -p admin --silent
RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/resources/datasource.cli

# config logstash logging
RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/resources/logs.cli

# Fix standalone history related error
RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history

COPY ./target/test.war /opt/jboss/wildfly/standalone/deployments

EXPOSE 8080 9990

CMD /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
