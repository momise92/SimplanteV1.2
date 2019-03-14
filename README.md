# SimplanteV1.2

This project provides productive setup for building Spring Boot Angular applications

This project uses following versions:  

* Spring Boot 2.1.3
* Angular 7.1
* Node and NPM 6.7.0

## Running the full application  
You can build the package as a single artifact by running the ./mvnw clean install. Next, you can run the application by executing:  

`$ java -jar backend/target/simplante-app.jar `  
The application will be accessible at `http://localhost:8080.` 

You can also run the application using Maven.  

``` 
$../mvnw spring-boot:run 
```

Running the frontend for development mode
Make sure to install npm on your development machine.

To install all the required binaries for your project, you can run following command.

``` 
$ cd src/main/frontend  
$ ../mvnw frontend:install-node-and-npm frontend:npm 
 ```
  
Once the above command finishes, you can start the frontend using the yarn start command.

Hot reloading
Both the front-end and back-end modules support hot reloading.
