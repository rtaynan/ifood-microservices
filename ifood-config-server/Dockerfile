FROM openjdk:11-jdk-slim
MAINTAINER Taynan Rezende <taynan.rezende@gmail.com>

# Set ENV variables
ENV PORT=8888

# Add JAR file and run it as entrypoint
ADD target/ifood-config-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD wget -q http://localhost:8888/actuator/health || exit 1
# Expose the port
EXPOSE 8888
