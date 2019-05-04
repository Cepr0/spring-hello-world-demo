package io.github.cepr0.demo;

import org.springframework.stereotype.Service;

@Service
public class GreeterTargetImpl implements GreeterTarget {

	private final Coin coin;

	public GreeterTargetImpl(Coin newCoin) {
		this.coin = newCoin;
	}

	@Override
	public String get() {
		if (coin.toss()) {
			return "World";
		} else {
			return "Spring";
		}
	}
}
