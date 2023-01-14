FROM maven:3.8.4-jdk-8-slim
WORKDIR /root/mavenTempData
ADD ./ ./
RUN mvn clean package -T 1C -Dmaven.test.skip=true -Dmaven.compile.fork=true

FROM openjdk:8-jre-slim

FROM nginx:latest
WORKDIR /root/
ADD ./main.db ./
COPY --from=0 /root/mavenTempData/target/ApiLighthouse-0.0.1-BETA.jar /root/
ADD ./src/main/resources/defaultTemplate.conf ./
# make a new directory to store the jdk files
RUN mkdir -p /usr/local/openjdk-8
COPY --from=1 /usr/local/openjdk-8 /usr/local/openjdk-8

# set environment variables
ENV JAVA_HOME /usr/local/openjdk-8
ENV JRE_HOME ${JAVA_HOME}/jre
ENV CLASSPATH .:${JAVA_HOME}/lib
ENV PATH ${PATH}:${JAVA_HOME}/bin

EXPOSE 8080

ENTRYPOINT ["java","-jar","/root/ApiLighthouse-0.0.1-BETA.jar"]
