FROM 192.168.56.102:5100/p_pub/amazoncorretto:17-alpine-jdk
#FROM amazoncorretto:17-alpine-jdk
WORKDIR ./
COPY ../target/springboot-helloworld.jar ./springboot-helloworld.jar
CMD ["java", "-jar", "springboot-helloworld.jar"]