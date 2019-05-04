package io.github.cepr0.demo;

import org.springframework.stereotype.Service;

@Service
public class Greeter {

	private final GreeterTarget target;

	public Greeter(GreeterTarget newTarget) {
		this.target = newTarget;
	}

	public String greet() {
		return "Hello " + target.get();
	}
}
