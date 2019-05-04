package io.github.cepr0.demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreeterTargetTest {
	@Test
	public void testGet() throws Exception {

		StubCoin coin = new StubCoin();

		GreeterTarget testedObject = new GreeterTargetImpl(coin);

		coin.setConstantResult(true);
		assertEquals("World", testedObject.get());

		coin.setConstantResult(false);
		assertEquals("Spring", testedObject.get());
	}
}