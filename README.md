# master-api
Master API to compile and centralize learnings and features in Spring Boot.

### Table of Contents
1. [Spring Framework](#spring-framework)
2. [Inversion of Control (IoC)](#inversion-of-control-ioc)
3. [Dependency Injection (DI)](#dependency-injection-di)
4. [Spring Boot](#spring-boot)
5. [Coupling](#coupling)

### Spring Framework
* Open-source Java platform
* Can be used in developing any Java application, but there are extensions for building web applications on top of the Java EE platform
* Key principles: [Inversion of Control](#inversion-of-control-ioc) and [Dependency Injection](#dependency-injection-di)

### Inversion of Control (IoC)
* Transfers the control of objects or portions of a program to a container or framework 
* Most commonly used in OOP 
* Enables a framework to take control of the flow of a program and make calls to programmers' custom code 
* Framework uses abstractions with additional behavior built in; programmers need to extend the classes of the framework or plugin our own classes
* Advantages 
  * Decoupling the execution of a task from its implementation makes it easier to switch between different implementations
  * Greater modularity of a program
  * Greater ease in testing a program by isolating a component or mocking its dependencies, and allowing components to communicate through contracts
* ApplicationContext (IoC in Spring)
  * Represents the IoC container 
  * Responsible for instantiating, configuring, and assembling objects know as beans, as well as managing their life cycles 
  * Several implementations: ClassPathXmlApplicationContext and FileSystemXmlApplicationContext for standalone applications, and WebApplicationContext for web applications

### Dependency Injection (DI)
* OOP revolves around the creation of objects, and some objects rely or depend on other objects (class A contains an object in class B)
* Pattern we can use to implement [IoC](#inversion-of-control-ioc), where the control being inverted is setting an object's dependencies: Connecting objects with other objects, or “injecting” objects into other objects, is done by an assembler rather than by the objects themselves 
* Constructor-Based Dependency Injection 
  * Container will invoke a constructor with arguments each representing a dependency we want to set 
  * @Autowired is used on top of the class constructor
  ```
  public class Person {
      private Address address; 
      
      @Autowired 
      public void Person(Address address) {
          this.address = address;
      }
  }
  ```
* Setter-Based Dependency Injection 
  * Container will call setter methods of our class after invoking a no-argument constructor or no-argument static factory method to instantiate the bean 
  * @Autowired is used on top of the class’s setter method
  ```
  public class Person {
      private Address address;
      
      @Autowired
      public void setAddress(Employee employee) {
          this.address = address;
      }
  }
  ```
* Field-Based Dependency Injection 
  * We inject the dependencies by marking them with the @Autowired annotation
  ```
  public class Person {
      @Autowired 
      private Address address;
  }
  ```
* Spring recommends using constructor-based injection for mandatory dependencies, and setter-based injection for optional ones 
* Spring, by default, wires the beans by their type 
* If there are more than one beans of the same type, we can use @Qualifier annotation to reference a bean by its name 
* Goal: [Loose coupling](#coupling)

### Spring Boot
* An extension of Spring framework
* Eliminates the boilerplate configurations required for setting up a Spring application
* Takes an opinionated view of the Spring platform, which paves the way for a faster and more efficient development ecosystem
* Makes it easy to create stand-alone, production-grade applications with embedded server (executable jar) whereas Spring apps must run on an external serer (deployed as WAR)
* Simplifies dependency management

### Controller
* @RestController vs @Controller
  * @RestController simplifies the creation of RESTful web services by combining the @Controller and @ResponseBody annotations, which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
  * Every request handling method of a @RestController class automatically serializes return objects into HttpResponse
  * Learn more [here](https://www.baeldung.com/spring-controller-vs-restcontroller) and [here](https://www.geeksforgeeks.org/difference-between-controller-and-restcontroller-annotation-in-spring/)
* @RequestMapping
  * Used to map web requests to Spring Controller methods
  * Can be used at the class (to establish the base URI) and method level (to determine which handle method will be used)
  * Common shorthand annotations (only supported at the method level):
    * @RequestMapping(method = RequestMethod.GET) &rarr; @GetMapping
    * @RequestMapping(method = RequestMethod.PUT) &rarr; @PutMapping
    * @RequestMapping(method = RequestMethod.POST) &rarr; @PostMapping
    * @RequestMapping(method = RequestMethod.DELETE) &rarr; @DeleteMapping
  * Allow default method: @RequestMapping()
  * Allow multiple URI: @RequestMapping(value = {"/method1", "/method2"}), @GetMapping(value = {"/method1", "/method2"})
  * Allow multiple method types: @RequestMapping(value = "/method1", method = {RequestMethod.POST, RequestMethod.GET}) 
  * Require headers to be present: @RequestMapping(value = "/method1", headers = {"name=samuelbmarks", "id=1"})
  * Specify consume and produce types: @RequestMapping(value = "/method1", produces = {"application/json","application/xml"}, consumes="text/html")
* See MasterApiController.java


### Coupling
* If class A contains a member object of class B, coupling is the degree of dependency of class A on class B 
* When you make some changes to class B, how often do you have to make corresponding changes to class A 
* Tight coupling 
  * Two classes often change together 
  * Reduces the flexibility and re-usability of the code 
* Loose coupling 
  * Reduces the dependencies of a class that uses the different class directly 
  * Less interdependency and information flow 
  * More testability 
  * Interfaces: Allow for multiple implementations that can be injected at runtime

