## Database configuration
H2 database with the name `twitterdb`. Credentials are in `/resources/application.properties`.  
The default ones are :

```
spring.datasource.url=jdbc:h2:mem:twitterdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

## Usage
Run the project through the IDE and head out to [http://localhost:8080](http://localhost:8080)
or
run this command in the command line:
```
mvn spring-boot:run
```
For database open : [http://localhost:8080/twitter-ui/](http://localhost:8080/twitter-ui/)

## Results
Databases:

   ![img.png](img.png)

   ![img_1.png](img_1.png)

   ![img_2.png](img_2.png)   

Output:
   
   Add Tweet:
   ![img_3.png](img_3.png)
   
   Like Tweet:
   ![img_4.png](img_4.png)
   
   ![img_5.png](img_5.png)

   Dislike Tweet:
   ![img_6.png](img_6.png)
   
   ![img_7.png](img_7.png)   

   Get Details:
   ![img_8.png](img_8.png)
   
   Get All Tweets:
   ![img_9.png](img_9.png)
   
   ![img_10.png](img_10.png)
