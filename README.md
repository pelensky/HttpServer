[![Build Status](https://travis-ci.org/pelensky/HttpServer.svg?branch=master)](https://travis-ci.org/pelensky/HttpServer)
[![Coverage Status](http://coveralls.io/repos/github/pelensky/HttpServer/badge.svg?branch=master)](https://coveralls.io/github/pelensky/HttpServer?branch=master)

## HTTP Server

This is a HTTP server built to pass these [Cob Spec Specifications](https://github.com/8thlight/cob_spec).

The tests tests to pass are as follows:
![tests](https://i.imgur.com/2T0QVTm.png)

#### Prerequisites 
1. Install [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

#### Cob spec running instructions
1. In your home folder, clone the repository `$ git clone https://github.com/pelensky/HttpServer`
2. Also in your home folder, clone the Cob Spec repository `$ git clone https://github.com/8thlight/cob_spec` 
3. In terminal, CD into the HttpServer repository `cd HttpServer`
4. Build the app, and run the unit tests by running `$ ./gradlew clean build`
5. Navigate to the cob_spec repository `$ cd ../cob_spec`
6. Install the packages `$ mvn package`
7. Start Fitnesse `java -jar fitnesse.jar -p 9090`
8. In a browser, navigate to [http://localhost:9090/](http://localhost:9090/)
9. Navigate to [HttpTestSuite](http://localhost:9090/HttpTestSuite)
10. Click [Edit](http://localhost:9090/HttpTestSuite?edit)
11. Update the SERVER_START_COMMAND variable to `!define SERVER_START_COMMAND {java -jar /Users/YOUR_COMPUTER_NAME/HttpServer/build/libs/HttpServer-1.0-SNAPSHOT.jar}`
12. Update the PUBLIC_DIR variable to `!define PUBLIC_DIR {/Users/YOUR_COMPUTER_NAME/cob_spec/public/}`
13. Click Save
14. Click [Suite](http://localhost:9090/HttpTestSuite?suite) to run all tests.