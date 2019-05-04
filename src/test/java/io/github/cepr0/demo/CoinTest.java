package io.github.cepr0.demo;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoinTest {
	@Test
	public void testToss() {
		StubRandom random = new StubRandom();

		Coin testedCoin = new CoinImpl(random);

		random.setConstantResult(true);
		assertTrue(testedCoin.toss());

		random.setConstantResult(false);
		assertFalse(testedCoin.toss());
	}
}