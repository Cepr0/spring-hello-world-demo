package io.github.cepr0.demo;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CoinImpl implements Coin {

	private final Random random;

	public CoinImpl(Random newRandom) {
		this.random = newRandom;
	}

	public boolean toss() {
		return random.nextBoolean();
	}
}
