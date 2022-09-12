FROM openjdk:18

RUN mkdir "/app"

ADD target/batch-processing.jar /app/batch-processing.jar
ADD /script.sh /app/script.sh
#RUN apt-get update
#RUN apt-get install -y netcat

#EXPOSE 8081

#ENTRYPOINT ["/app/script.sh"]
#CMD ["nginx"]
#ENTRYPOINT ["/script.sh"]
CMD java -jar /app/batch-processing.jar