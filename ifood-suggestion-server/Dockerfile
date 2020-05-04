FROM openjdk:11-jdk-slim
MAINTAINER Taynan Rezende <taynan.rezende@gmail.com>

# Set ENV variables
ENV CONFIG_URL="http://config:8888"
ENV EUREKA_URL="http://eureka:8761"
ENV REDIS_URL="redis"

# Add JAR file and run it as entrypoint
ADD target/ifood-suggestion-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 8082