## Spring Client Resolver

Spring Client resolver is a Spring Boot library that helps you determine Clients from where your application endpoint is
called. The resolved client information includes following:

* Client Name. eg. Chrome, Safari, Edge etc.
* Client Version.
* Device OS.
* OS Version.
* Device type such as mobile/tablet/desktop etc.

### How to use

in order to use the library in your Spring Boot project, add below dependency in your **pom.xml**

```xml

<dependency>
    <groupId>io.github.114snehasish</groupId>
    <artifactId>spring-client-resolver</artifactId>
    <version>1.0.0</version>
</dependency>
```

For Gradle projects, add this to your **build.gradle** file:

```groovy
implementation group: 'io.github.114snehasish', name: 'spring-client-resolver', version: '1.0.0'
```

Crate a Spring Configuration file and add below piece of code:

```java

@Configuration
@EnableWebMvc
public class ResolverConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(ClientInfoAttributeResolver.getDefaults());
    }
}
```

Next, in whichever `Controller` or `RestController` method you need Client information, you can simply inject it like
below:

```java
@GetMapping
public ClientInfo getClientInfo(ClientInfo clientInfo){
        //Do whatever you want with the clientInfo object.
        }
```

### How does it work?

Behind the scene it uses [uap-java](https://github.com/ua-parser/uap-java) library, thus having support to Browsers,
Rest Clients such as Postman, CLI tools such as Curl, WebCrawlers, Runtimes etc. This library is just a Spring wrapper
on top of this `uap-java`.