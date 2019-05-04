package io.github.cepr0.demo;

import java.util.Random;

public class StubRandom extends Random {

	private boolean constantResult;

	public void setConstantResult(final boolean newResult) {
		this.constantResult = newResult;
	}

	@Override
	public boolean nextBoolean() {
		return constantResult;
	}
}
