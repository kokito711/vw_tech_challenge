# vw_tech_challenge

Backend Technical Challenge implemented by Sergio López Jiménez

## About the project

### Technologies

To implement this project I have chosen to use Spring Boot based on Java 17 as framework and language.
Along with Spring Boot, I decided to include the following dependencies:

- Lombok as annotation processor because simplifies a lot the definition of some constructors and methods such as Equals
  or HashCode
- Apache commons-lang3 which contains some utils to manage strings and also the object ```Pair``` which will be used
  in the project instead of a map because I believe it is easier to manage the workplace size with this class.
- AssertJ to be able to perform awesome assertions in the unit testing.

### Design and architecture

I tried to adapt the project to a hexagonal architecture ih which can be found three parts:

- Application layer
- Domain layer
- Infrastructure layer

In this project I have not found any kind of persistence, so there are no adapters in the code but one port which is
responsible to expose the domain logic through the ```RobotManagementPort``` interface.

The application layer contains a rest endpoint that will provide the entrypoint of the application. I have chosen this
kind of entry because is one of the easiest ways to send information and expose a service. Although is a hexagonal
architecture it could be replaced without problem since the Rest Controller loads a bean of ```RobotManagementPort```
interface so there is no coupling.
In this layer the received data is converted into "scenarios" that will be executed by robots in the domain layer.
There is also some exceptions treatment in order to manage the possible exceptions thrown in the domain layer.

The infrastructure layer contains in this case only the definition of the beans since the hexagonal architecture
principles prevent to define "services" or "components" in the domain code and let this responsibility to the
infrastructure layer.

The domain layer contains the business logic, and it is based on DDD. That means we have an aggregate entity
```Robot``` in which the value classes and other entities depends on.

Besides this, there is one class that contains the business logic executing scenarios and using the robots for that.

### Assumptions

- The entrypoint is an API REST
- The matrix of the workplaces starts with 0 and goes to the given number in the first String row. That means the size
  is (x+1,y+1). Otherwise, the example outputs will not be produced.
- The given string contains spaces between workspace sizes (e.g.: 5 5) and the initial position (e.g.: 1 2 N) but not in
  the instructions list.

### Testing

In this project I decided to include some unit tests which are fast and easy to implement
(and cover more than the 90% of the domain code if you use the coverage test run in IntelliJ)
Besides that, I have included some integration tests to test the interaction between layers and the expected
inputs/outputs when the API is called.
The controlled errors are also tested with integration tests.