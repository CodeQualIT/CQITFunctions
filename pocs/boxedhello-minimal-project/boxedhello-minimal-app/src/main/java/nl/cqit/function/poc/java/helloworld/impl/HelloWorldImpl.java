package nl.cqit.function.poc.java.helloworld.impl;

import nl.cqit.function.poc.java.helloworld.api.HelloWorldApi;
import nl.cqit.function.poc.java.helloworld.api.model.Person;

public class HelloWorldImpl implements HelloWorldApi {

    @Override
    public String sayHello(Person person) {
        if (person.getLastName() == null) {
            return "Hello, %s!".formatted(person.getFirstName());
        }
        return "Hello, %s %s!".formatted(person.getFirstName(), person.getLastName());
    }
}
