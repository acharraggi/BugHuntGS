Bug Hunt Game Server
====================

A working Dropwizard demonstration project. It has a simple API to save player scores and get the top 10 scores. 

It was built in Eclipse using Maven. It makes use of Dropwizard 0.7.0-rc3 for the restful API. Swagger is used to document the API, and Dropwizard-swagger for the Swagger-UI. Also makes use of Junit for some unit tests and Logback for logging. There's also a Soap-UI project for even more testing and an alternate to using Swagger-UI to execute the API.

How to use it.

- get from GitHub
- do a Maven Package build

cd C:/Users/Mike/git/BUGHUN~1/BUGHUN~1   ? - probably don't mention this.

Start the API.
java -jar target/BugHuntGameServer-0.0.1-SNAPSHOT.jar server bug-hunt.yml 


View the documentation at: 
http://localhost:8080/swagger-ui/
Swagger lets you construct API messsages, submit them and view the responses.

An alternate way to execute the API is via Soap-UI. A Soap-UI project file is at:
BugHuntGameServer\src\test\soapUI\BugHunt-soapui-project.xml