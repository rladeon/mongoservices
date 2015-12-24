# mongoservices

The purpose of this project is to provide an example of setting up a micro-services based architecture in J2EE environment. To do this I used **Dropwizard** which is a framework allowing to implement in other autonomous and self-supporting applications. Which is particularly useful for the creation of micro-services. In this example, the application offers the CRUD functions necessary to update a NoSQL database **MongoDB** mase here.

## Read ##

curl http://localhost:8080/

## Add ##

curl -i -X POST -H "Content-Type: application/json" -d '{"mark":"FORD"}' http://localhost:8080/add
