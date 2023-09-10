# Consuming Rest

Service that will accept HTTP GET requests.

See also: https://spring.io/guides/gs/consuming-rest/

Needs https://github.com/ashburnere/spring-quoters to run which serves an example Rest API.

Uses: 
* A `RestTemplate`, which uses the Jackson JSON processing library to process the incoming
  data.
* A `CommandLineRunner` that runs the `RestTemplate` (and, consequently, fetches our
  quotation) on startup.

Finally, you need to set the server port. The quoters application uses the default
server port, 8080, so this application cannot also use the same port. You can set
the server port to 8081 by adding the following line to applicaiton properties
(which the Initializr created for you): `server.port=8081`


Run `./gradlew build` to build the code.

Run `./gradlew bootRun` to build the code and run the app.


You should see output similar to the following but with a random quotation:

`2019-08-22 14:06:46.506  INFO 42940 --- [           main] c.e.c.ConsumingRestApplication           :Quote{type='success', value=Value{id=1, quote='Working with Spring Boot is like pair-programming with the Spring developers.'}}`


