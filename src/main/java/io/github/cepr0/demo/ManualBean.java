package io.github.cepr0.demo;

public class ManualBean {

	private final Coin coin;

	public ManualBean(Coin coin) {
		this.coin = coin;
	}

	public boolean toss() {
		return coin.toss();
	}
}
