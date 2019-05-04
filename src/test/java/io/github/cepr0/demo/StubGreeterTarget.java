package io.github.cepr0.demo;

public class StubGreeterTarget implements GreeterTarget {
	@Override
	public String get() {
		return "TEST";
	}
}
