package nl.cqit.function.poc.java.boxedhello.controller;


import nl.cqit.function.poc.java.boxedhello.BoxedHelloApi;
import nl.cqit.function.poc.java.boxedhello.services.helloworld.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/")
public class BoxedHelloController implements BoxedHelloApi {

	@Autowired
	HelloWorldService helloWorldService;

	@Override
	public String sayHello(Person person) {
		String greeting = helloWorldService.sayHello(person);
		String horizontalEdge = "+" + "-".repeat(greeting.length() + 2) + "+";
		return horizontalEdge + "\n| " + greeting + " |\n" + horizontalEdge;
	}
}
