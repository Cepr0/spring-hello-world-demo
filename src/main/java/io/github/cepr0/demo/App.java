package io.github.cepr0.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
@ComponentScan
@EnableAsync
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

	public static void main(final String[] args) throws InterruptedException {
		var context = new AnnotationConfigApplicationContext(App.class);
		App.context = context;
		context.publishEvent(new ReadyEvent(context));
		SECONDS.sleep(2);
		context.close();
	}

	@Bean
	public Random random() {
		return new Random();
	}

	@Bean
	public ManualBean manualBean(ApplicationContext context) {
		var factory = context.getAutowireCapableBeanFactory();
		return factory.createBean(ManualBean.class);
	}

	@Bean
	public TaskExecutor taskExecutor() {
		CustomizableThreadFactory factory = new CustomizableThreadFactory();
		factory.setThreadNamePrefix("task-");
		return new SimpleAsyncTaskExecutor(factory);
	}

	@Async
	@EventListener
	public void onAppReady(ContextRefreshedEvent event) {
		ApplicationContext context = (ApplicationContext) event.getSource();
		Coin coin = context.getBean(Coin.class);
		LOGGER.info("Coin toss: {}", coin.toss());

		ManualBean manualBean = (ManualBean) context.getBean("manualBean");
		LOGGER.info("Manual bean: " + manualBean.toss());

		ManualBean mb = (ManualBean) context.getBean("manualB");
		LOGGER.info("Manual defined bean: " + manualBean.toss());

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
