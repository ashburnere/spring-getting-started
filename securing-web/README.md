#[ Securing a Web Application]()

A simple web (MVC) application with resources that are protected by Spring Security. 

See also: https://spring.io/guides/gs/securing-web/

Run `./gradlew build` to build the code.

Run `./gradlew bootRun` to build the code and run the app.

Secures the page with a login form that is backed by a fixed list of users.

The web application includes two simple views: a home page and a “Hello, World” page.
The home page is defined in the Thymeleaf template home.html (src/main/resources/templates/home.html).
The home view includes a link to the /hello page, which is defined in the Thymeleaf template hello.html 
(from src/main/resources/templates/hello.html).

The login view presents a form that captures a username and password and posts them to /login. As configured, 
Spring Security provides a filter that intercepts that request and authenticates the user. 
If the user fails to authenticate, the page is redirected to /login?error, and your page displays 
the appropriate error message. Upon successfully signing out, the application is sent to /login?logout, and the page 
displays the appropriate success message.


