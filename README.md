## Bug Hunt Game Server

A working Dropwizard demonstration project. It has a simple API to save player scores and get the top 10 scores. 

It was built in Eclipse using Maven and Git. 
It makes use of [Dropwizard 0.7.0-rc3](https://dropwizard.github.io/dropwizard/) for the restful API. 
[Swagger](https://helloreverb.com/developers/swagger) is used to document the API,
and [dropwizard-swagger](https://github.com/federecio/dropwizard-swagger) for the Swagger user interface to the docs. 
Also makes use of Junit for some unit tests and Logback for logging. 
There's also a [Soap-UI](http://www.soapui.org/) project for even more testing and an alternate to using Swagger-UI to execute the API.

# How To Build It

I started with [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/keplersr2).

Get the Bug Hunt Game Server code from GitHub.
File -> Import -> Git -> Projects from Git -> Clone URI.
https://github.com/acharraggi/BugHuntGS.git

- do a Maven Package build

Start the API:
java -jar target/BugHuntGameServer-0.0.1-SNAPSHOT.jar server bug-hunt.yml 

View the documentation at: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)
Swagger lets you construct API messsages, submit them and view the responses.

An alternate way to execute the API is via Soap-UI. A Soap-UI project file is at:
BugHuntGameServer\src\test\soapUI\BugHunt-soapui-project.xml

