FROM openjdk:21
EXPOSE 8081
COPY ./target/Springboot-Learning-0.0.1-SNAPSHOT.jar springboot-learning-app.jar
CMD ["java","-jar","springboot-learning-app.jar"]
#docker build -t springboot-learning-app . -> to build image
#docker run -p 8081:8080 springboot-learning-app -> to run the image(you are telling Docker to map port 8081 on your host machine to port 8080 inside the Docker container)
#docker ps -> running containers
#docker stop springboot-learning-app -> to stop container