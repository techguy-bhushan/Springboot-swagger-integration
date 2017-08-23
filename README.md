**Swagger**
Swagger 2 is an open-source project used to describe and document RESTful APIs.

The Swagger 2 specification, which is known as OpenAPI specification, has several implementations. Currently, 
Springfox that has replaced Swagger-SpringMVC (Swagger 1.2 and older) is popular for Spring Boot applications. 

- To bring it in, we need the following dependency declaration in our Maven POM.

`<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.6.1</version>
</dependency>`


- In addition to Sprinfox, we also require Swagger UI. The code to include Swagger UI is this.


`<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.6.1</version>
    <scope>compile</scope>
</dependency>`


- Configuring Swagger 2 in the Application
we will create a Docket bean in a Spring Boot configuration to configure Swagger 2 for the application. 
A Springfox Docket instance provides the primary API configuration with sensible defaults and convenience methods for configuration. 

You can see the e.g the swagger config in below package
`com.swagger.swagger.config.SwaggerConfig`

you can read much about it from [here](http://springfox.github.io/springfox/docs/current/)

You need to run this project and after run this project enter the url -> <http://localhost:8080/swagger-ui.html>
you will see there beautiful and helpful docs for the rest apis auto created by swagger with in less configuration.

