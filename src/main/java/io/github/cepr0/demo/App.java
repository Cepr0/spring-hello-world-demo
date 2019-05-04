package io.github.cepr0.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Random;

@Configuration
@ComponentScan
public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	private static ApplicationContext context;

	private Greeter greeter;

	@Autowired
	public void setGreeter(Greeter greeter) {
		this.greeter = greeter;
	}

	public static ApplicationContext context() {
		return context;
	}

	public static void main(final String[] args) {
		var context = new AnnotationConfigApplicationContext(App.class);
		App.context = context;
		context.publishEvent(new ReadyEvent(context));
		context.close();
	}

	@Bean
	public Random random() {
		return new Random();
	}

	@EventListener
	public void onAppReady(ReadyEvent event) {
		Coin coin = event.getContext().getBean(Coin.class);
		LOGGER.info("Coin toss: {}", coin.toss());

		LOGGER.info("Greetings: {}", greeter.greet());
	}

	public static class ReadyEvent {
		private final ApplicationContext context;

		public ReadyEvent(final ApplicationContext context) {
			this.context = context;
		}

		public ApplicationContext getContext() {
			return context;
		}
	}
}
