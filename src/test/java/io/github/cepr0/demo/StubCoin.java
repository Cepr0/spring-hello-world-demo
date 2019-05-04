package io.github.cepr0.demo;

public class StubCoin implements Coin {

	private boolean constantResult;

	public final void setConstantResult(final boolean newResult) {
		this.constantResult = newResult;
	}

	public boolean toss() {
		return constantResult;
	}
}
