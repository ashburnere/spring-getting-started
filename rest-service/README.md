# Rest Service

Service that will accept HTTP GET requests.

See also: https://spring.io/guides/gs/rest-service/

Run `./gradlew build` to build the code.

Run `./gradlew bootRun` to build the code and run the app.

Now that the service is up, visit http://localhost:8080/greeting, where you should see:
`{"id":1,"content":"Hello, World!"}`

Provide a name query string parameter by visiting http://localhost:8080/greeting?name=User. Notice how the value of the content attribute changes from Hello, World! to Hello, User!, as the following listing shows:
`{"id":2,"content":"Hello, User!"}`


