# Read Me First

This project process and update batch data to mysql database.Only Admin user can operate the operation.User with other 
role can view the uploaded data.A small client application is included to verify the endpoint and to see the result.
Client Application to call the api - http://localhost:8081/ (after deployment)

Developed by :

Mulugeta Abrha Assefa

Khandokar Sabbir Ahmed

# Getting Started

### Model
    There are 3 models: User, Role, Student
### API endpoint

#### Register 

    Endpoint:  api/auth/register
    Body: {
        "username":"",
        "email": "",
        "password": "",
        "role": "admin"
        }
    HTTP method: "POST"

#### Signin

    Endpoint: api/auth/signin
    Body: {
        "username": "",
        "password": ""
        }
    Method: "POST"

This generates a JWT token for  authorization and then use the token as header in authorization field to start the batch

#### Run Batch
    
    Endpoint: api/test/batch
    Header: "Authorization": "BEARER ....JWT Token...."
    HTTP Method: "GET"    

#### Show Data

    Endpoint: api/test/list
    Header: "Authorization": "BEARER ....JWT Token...."
    HTTP Method: "GET"    

With admin permission it will run the batch. It uses spring batch item reader, processing and writer to process the data

### Docker 

Before uploading in docker, three files "Dockerfile", "docker-compose.yml" and "application-properties" need to configure.

### Dockerfile
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

### docker-compose.yml

    version: "3.9"

    services:
      mysqldb:
       image: mysql:latest
       ports:
        - 3307:3306
       healthcheck:
       test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
       timeout: 30s
       retries: 10
       restart: on-failure

       environment:
        - MYSQL_DATABASE=batch
        - MYSQL_ROOT_PASSWORD=root
      app:
        build: .
        ports:
        - 8081:8081
        environment:
        - MYSQL_URL=mysqldb

        depends_on:
          mysqldb:
           condition: service_healthy

Then run command docker compose up. Two separate image will create, one for application and another is for mysql Database. 
