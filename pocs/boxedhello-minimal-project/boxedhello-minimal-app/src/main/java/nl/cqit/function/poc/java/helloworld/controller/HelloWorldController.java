package nl.cqit.function.poc.java.helloworld.controller;


import nl.cqit.function.poc.java.helloworld.impl.HelloWorldImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class HelloWorldController extends HelloWorldImpl {
}
