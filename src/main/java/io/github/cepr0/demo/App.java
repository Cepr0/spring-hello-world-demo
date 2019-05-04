package io.github.cepr0.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Random;
import java.util.function.Function;

@Configuration
@ComponentScan("io.github.cepr0.demo")
public class App {

	private static ApplicationContext context;

	private Greeter greeter;
	private StatConsumer statConsumer;

	@Autowired
	public void setGreeter(Greeter greeter) {
		this.greeter = greeter;
	}

	@Autowired
	public void setStatConsumer(StatConsumer statConsumer) {
		this.statConsumer = statConsumer;
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

	@Bean
	public Function<String, StatWriter> statWriter() {
		return StatWriter::new;
	}

	@EventListener
	public void onAppReady(ReadyEvent event) {
		Coin coin = event.getContext().getBean(Coin.class);
		System.out.println(coin.toss());

		System.out.println(greeter.greet());

		statConsumer.invoke("param");
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
