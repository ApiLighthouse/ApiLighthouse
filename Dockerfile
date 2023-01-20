FROM maven:3.6.0-alpine
WORKDIR /root/mavenTempData
ADD ./backend ./backend
ADD pom.xml ./pom.xml
ADD ./.m2/  /root/.m2/
RUN mvn clean package -T 1C -Dmaven.test.skip=true -Dmaven.compile.fork=true

FROM openjdk:8u111-jre-alpine

FROM nginx:alpine-slim
WORKDIR /root/
ADD backend/main.db ./
COPY --from=0 /root/mavenTempData/backend/target/backend-0.0.1-BETA.jar /root/
ADD backend/src/main/resources/defaultTemplate.conf ./
# make a new directory to store the jdk files
RUN mkdir -p /usr/lib/jvm/java-1.8-openjdk
COPY --from=1 /usr/lib/jvm/java-1.8-openjdk /usr/lib/jvm/java-1.8-openjdk
#RUN apk add libstdc++6 libstdc++
#RUN chmod -R 777 /usr/lib/jvm/java-1.8-openjdk
COPY --from=0 /usr/lib/libstdc* /usr/lib/jvm/java-1.8-openjdk/lib/

# set environment variables
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV JRE_HOME ${JAVA_HOME}/jre
ENV CLASSPATH .:${JAVA_HOME}/lib
ENV PATH ${PATH}:${JAVA_HOME}/bin

EXPOSE 8080

#ENTRYPOINT ["/bin/sh"]

ENTRYPOINT ["java","-jar","/root/backend-0.0.1-BETA.jar"]
