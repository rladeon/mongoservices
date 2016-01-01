# mongoservices

The purpose of this project is to provide an example of setting up a micro-services based architecture in J2EE environment. To do this I used **Dropwizard** with dependency management made with the help of **Guice**. Dropwizard is a framework allowing to implement in other autonomous and self-supporting applications. Which is particularly useful for the creation of micro-services. In this example, the application offers the CRUD functions necessary to update a NoSQL database **MongoDB** mase here.

## Read ##

curl http://localhost:8080/

## Add ##

curl -i -X POST -H "Content-Type: application/json" -d '{"mark":"FORD"}' http://localhost:8080/crud/add
## Edit ##

curl -i -X POST -H "Content-Type: application/json" -d '{"mark":"FORD"}' http://localhost:8080/crud/edit/1
## Delete ##

curl -i -X DELETE -H "Content-Type: application/json" http://localhost:8080/crud/delete/1
## Maven command ##
### build & start ###

mvn compile exec:java
