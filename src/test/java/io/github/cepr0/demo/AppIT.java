package io.github.cepr0.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(
		loader = AnnotationConfigContextLoader.class,
		classes = App.class
)
@RunWith(SpringRunner.class)
public class AppIT {

	@Autowired private ApplicationContext context;

	@Test
	public void testSpring() {
		Greeter greeter = context.getBean(Greeter.class);
		assertTrue(greeter.greet().startsWith("Hello"));
	}
}
